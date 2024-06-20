package org.example.it355pz.service.impl;

import org.example.it355pz.model.UsersEntity;
import org.example.it355pz.repository.UserRepository;
import org.example.it355pz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UserService {
    @Autowired
    private UserRepository usersRepository;

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity getUserById(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public UsersEntity saveUser(UsersEntity user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
}
