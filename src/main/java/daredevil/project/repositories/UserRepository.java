package daredevil.project.repositories;

import daredevil.project.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    List<User> getUsersByType(String type);
    User getUserByName(String name);
    List<User> getUnoccupiedLandLords();
}
