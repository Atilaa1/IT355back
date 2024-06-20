package org.example.it355pz.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.it355pz.model.enums.RoleType;
import org.example.it355pz.model.UsersEntity;
import org.example.it355pz.repository.UserRepository;
import org.example.it355pz.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        UsersEntity user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Wrong pass");
        }
        return jwtService.generateToken(user);
    }

    @Override
    public String register(String username, String password, String mail, RoleType role) {
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setMail(mail);
        user.setRole(role);

        userRepository.save(user);

        return jwtService.generateToken(user);
    }
}

