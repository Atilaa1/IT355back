package org.example.it355pz.service;

import org.example.it355pz.model.enums.RoleType;

public interface AuthService {
    String login(String username, String password);

    String register(String username, String password,String mail, RoleType role);
}