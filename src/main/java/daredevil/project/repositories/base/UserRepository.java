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
    User getUserByName(String name) throws CantCreateUserException;
    User getUserByLoginModel(String name, String password) throws NoUserFoundException;
    String isUserFree(UserDTO userDTO);
}
