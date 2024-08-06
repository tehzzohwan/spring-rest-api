package com.tehzzcode.bezexample.payload;

import com.tehzzcode.bezexample.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class SignupRequestDto {

    @NotBlank(message = "The name field is required.")
    @Size(min = 3, max = 20, message = "The name must be from 3 to 20 characters.")
    private String name;

    @NotBlank(message = "The age field is required.")
    @Min(value = 18, message = "The age must be equal or greater than 18")
    private int age;

    @NotBlank(message = "The email is required.")
    @Email(pattern =)
    private String email;
    private String password;
    private String gender;
    private Role role;
}
