package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.servieces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping("/new")
    public void createUser(@RequestBody User user) {
        service.createUser(user);
    }

    @GetMapping
    public List<User> getUser() {
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.delete(id);
    }
}
