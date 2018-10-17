package com.example.demo.servieces;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void update(int id, User user);
    void delete(int id);
}
