package org.example.it355pz.controller;

import org.example.it355pz.model.UsersEntity;

import org.example.it355pz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService usersService;

    @GetMapping
    public List<UsersEntity> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersEntity getUserById(@PathVariable int id) {
        return usersService.getUserById(id);
    }

    @PostMapping
    public UsersEntity createUser(@RequestBody UsersEntity user) {
        return usersService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UsersEntity updateUser(@PathVariable int id, @RequestBody UsersEntity user) {
        user.setId(id);
        return usersService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
    }
}
