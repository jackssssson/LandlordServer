package daredevil.project.repositories;

import daredevil.project.models.User;

public interface UserRepository {
    void createUser(User user);
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
}
