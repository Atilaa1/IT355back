package org.example.it355pz.service;

import org.example.it355pz.model.UsersEntity;

import java.util.List;

public interface UserService {
    List<UsersEntity> getAllUsers();
    UsersEntity getUserById(int id);
    UsersEntity saveUser(UsersEntity user);
    void deleteUser(int id);
}
