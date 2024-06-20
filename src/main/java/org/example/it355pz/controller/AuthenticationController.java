package org.example.it355pz.controller;

import lombok.RequiredArgsConstructor;
import org.example.it355pz.model.enums.RoleType;

import org.example.it355pz.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String mail,
                           @RequestParam RoleType role) {
        return authService.register(username,password,mail, role);
    }
}

