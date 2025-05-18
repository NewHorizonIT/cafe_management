package com.springboot.springboot.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import com.springboot.springboot.dto.request.AuthenticationRequest;
import com.springboot.springboot.dto.request.IntrospectRequest;
import com.springboot.springboot.dto.response.AuthenticationResponse;
import com.springboot.springboot.dto.response.IntrospectResponse;
import com.springboot.springboot.entity.User;
import com.springboot.springboot.exception.AppException;
import com.springboot.springboot.exception.ErrorCode;
import com.springboot.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Value("${jwt.valid-duration}")
    private long VALID_DURATION;

    @Value("${jwt.refreshable-duration}")
    private long REFRESHABLE_DURATION;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
            log.warn("Token introspection failed: {}", e.getMessage());
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        log.debug("Attempting authentication for username: {}", request.getUsername());

        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", request.getUsername());
                    return new AppException(ErrorCode.USER_NOT_EXISTED);
                });

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            log.error("Authentication failed for username: {} - Invalid password", request.getUsername());
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        log.info("Authentication successful for username: {}", request.getUsername());
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            String token = jwsObject.serialize();
            log.info("Generated JWT token for user: {}", user.getUsername());
            return token;
        } catch (JOSEException e) {
            log.error("Cannot create token for user {}: {}", user.getUsername(), e.getMessage(), e);
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) {
            log.error("Token verification failed: verified={}, expiryTime={}", verified, expiryTime);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        log.debug("Token verified successfully");
        return signedJWT;
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        Map<String, List<String>> rolesAndPermissions = userRepository.findRolesAndPermissionsByUserId(user.getId());

        List<String> roles = rolesAndPermissions.getOrDefault("roles", Collections.emptyList());
        List<String> permissions = rolesAndPermissions.getOrDefault("role_permissions", Collections.emptyList());

        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> stringJoiner.add("ROLE_" + role));
        }

        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.forEach(stringJoiner::add);
        }

        String scope = stringJoiner.toString();
        log.debug("Built scope for user {}: {}", user.getUsername(), scope);
        return scope;
    }
}