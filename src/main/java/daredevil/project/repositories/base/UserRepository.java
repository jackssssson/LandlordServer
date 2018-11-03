package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id) throws NoUserFoundException;
    void updateUser(int id, User user);
    void deleteUser(int id);
    List<User> getUsersByType(String type);
    User getUserByName(String name) throws CantCreateUserException;
    User getUserByLoginModel(String name, String password) throws NoUserFoundException;
    boolean checkUserLogin(String name, String password);
    String isUserFree(UserDTO userDTO);
}
