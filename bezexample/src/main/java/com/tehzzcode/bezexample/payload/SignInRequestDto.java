package com.tehzzcode.bezexample.payload;

import lombok.Data;

@Data
public class SignInRequestDto {

    private String username;
    private String password;
}
