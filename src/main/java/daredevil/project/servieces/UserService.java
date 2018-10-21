package daredevil.project.servieces;

import daredevil.project.models.User;

public interface UserService {
    void createUser(User user);
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
}
