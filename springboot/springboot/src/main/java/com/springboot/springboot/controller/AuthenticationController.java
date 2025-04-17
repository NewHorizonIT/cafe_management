package com.springboot.springboot.controller;

import com.springboot.springboot.dto.request.AuthenticationRequest;
import com.springboot.springboot.dto.request.IntrospectRequest;
import com.springboot.springboot.dto.request.LogoutRequest;
import com.springboot.springboot.dto.request.RefreshRequest;
import com.springboot.springboot.dto.response.ApiResponse;
import com.springboot.springboot.dto.response.AuthenticationResponse;
import com.springboot.springboot.dto.response.IntrospectResponse;
import com.springboot.springboot.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db_cafe_management/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
                .code("200")
                .message("Success")
                .result(response)
                .build());
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request)
            throws Exception {
        IntrospectResponse response = authenticationService.introspect(request);
        return ResponseEntity.ok(ApiResponse.<IntrospectResponse>builder()
                .code("200")
                .message("Success")
                .result(response)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody LogoutRequest request) throws Exception {
        authenticationService.logout(request);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code("200")
                .message("Success")
                .build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> refreshToken(@RequestBody RefreshRequest request)
            throws Exception {
        AuthenticationResponse response = authenticationService.refreshToken(request);
        return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
                .code("200")
                .message("Success")
                .result(response)
                .build());
    }
}
