package com.example.demo.repositories;

import com.example.demo.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void update(int id, User user);
    void delete(int id);
}
