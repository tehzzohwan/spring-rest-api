package com.tehzzcode.bezexample.controller;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.payload.JwtAuthenticationResponse;
import com.tehzzcode.bezexample.payload.RefreshTokenRequest;
import com.tehzzcode.bezexample.payload.SignInRequestDto;
import com.tehzzcode.bezexample.payload.SignupRequestDto;
import com.tehzzcode.bezexample.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signup(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequestDto signInRequestDto){
        return ResponseEntity.ok(authService.signin(signInRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authService.requestToken(refreshTokenRequest));
    }
}
