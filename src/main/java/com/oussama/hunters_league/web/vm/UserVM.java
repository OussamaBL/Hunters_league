package com.oussama.hunters_league.web.vm;

import com.oussama.hunters_league.domain.Enum.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVM {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "CIN is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "CIN must be alphanumeric")
    private String cin;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Nationality is required")
    private String nationality;


    private LocalDateTime joinDate=LocalDateTime.now();

    @Future(message = "license Expiration date should be in future")
    private LocalDateTime licenseExpirationDate;

    @NotNull(message = "Role is required")
    private Role role;

}
