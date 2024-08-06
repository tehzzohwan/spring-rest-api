package com.tehzzcode.bezexample.service.impl;

import com.tehzzcode.bezexample.entities.Customer;
import com.tehzzcode.bezexample.entities.Role;
import com.tehzzcode.bezexample.payload.JwtAuthenticationResponse;
import com.tehzzcode.bezexample.payload.RefreshTokenRequest;
import com.tehzzcode.bezexample.payload.SignInRequestDto;
import com.tehzzcode.bezexample.payload.SignupRequestDto;
import com.tehzzcode.bezexample.repository.CustomerRepository;
import com.tehzzcode.bezexample.service.AuthService;
import com.tehzzcode.bezexample.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public Customer signup(SignupRequestDto signupRequestDto) {
        Customer customer = new Customer();

        customer.setAge(signupRequestDto.getAge());
        customer.setEmail(signupRequestDto.getEmail());
        customer.setGender(signupRequestDto.getGender());
        customer.setName(signupRequestDto.getName());
        customer.setRole(Role.USER);
        customer.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));

        return customerRepository.save(customer);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequestDto signInRequestDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInRequestDto.getUsername(), signInRequestDto.getPassword());
            System.out.println(usernamePasswordAuthenticationToken);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            var customer = customerRepository.findByEmail(signInRequestDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            var jwt = jwtService.generateToken(customer);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), customer);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            return jwtAuthenticationResponse;
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    public JwtAuthenticationResponse requestToken(RefreshTokenRequest refreshTokenRequest) {
        String customerEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Customer customer = customerRepository.findByEmail(customerEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), customer)) {
            var jwt = jwtService.generateToken(customer);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
