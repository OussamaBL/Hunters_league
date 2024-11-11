package com.oussama.hunters_league.web.vm.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVM {

    @Nullable
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @Nullable
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, and one number"
    )
    private String password;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    @Email(message = "Email should be valid")
    private String email;

    @Nullable
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "CIN must be alphanumeric")
    private String cin;

    @Nullable
    private String nationality;

}
