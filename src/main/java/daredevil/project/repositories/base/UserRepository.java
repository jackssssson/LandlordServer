package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Models.LoginModel;
import daredevil.project.models.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    List<User> getUsersByType(String type);
    User getUserByName(String name);
    User getUserByLoginModel(String name, String password) throws NoUserFountEsception;
    List<User> getUnoccupiedLandLords();
    public boolean checkUserLogin(String name, String password);
    public String isUserFree(UserDTO userDTO);
}
