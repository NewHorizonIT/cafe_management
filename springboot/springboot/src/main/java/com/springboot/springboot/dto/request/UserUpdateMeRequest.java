package com.springboot.springboot.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateMeRequest {
    @Email(message = "Email must be valid")
    String email;

    @Size(min = 10, max = 11, message = "Phone must be between 10 and 11 characters")
    String phone;
}

