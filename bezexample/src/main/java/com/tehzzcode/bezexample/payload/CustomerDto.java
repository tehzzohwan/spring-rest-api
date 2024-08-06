package com.tehzzcode.bezexample.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CustomerDto {

    @NotBlank(message = "The name is required.")
    @Size(min = 3, max = 20, message = "The name must be from 3 to 20 characters.")
    private String name;

    @NotNull(message = "The age is required.")
    @Min(value = 18, message = "The age must be equal or greater than 18")
    private int age;


}
