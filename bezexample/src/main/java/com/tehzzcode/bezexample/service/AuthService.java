package com.tehzzcode.bezexample.service;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.JwtAuthenticationResponse;
import com.tehzzcode.bezexample.payload.RefreshTokenRequest;
import com.tehzzcode.bezexample.payload.SignInRequestDto;
import com.tehzzcode.bezexample.payload.SignupRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Customer signup(SignupRequestDto signupRequestDto);

    JwtAuthenticationResponse signin(SignInRequestDto signInRequestDto);

    JwtAuthenticationResponse requestToken(RefreshTokenRequest refreshTokenRequest);
}
