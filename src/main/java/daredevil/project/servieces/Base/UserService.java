package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id) throws NoUserFoundException;
    void updateUser(int id, User user);
    void deleteUser(int id);
    void addEstate(Estates estates);
    List<User> getUsersByType(String type);
    User getUserByName(String name) throws CantCreateUserException;
    Estates getEstateByUserName(String user);
    boolean updateEstate(int id, Estates estates);
    User getUserByLoginModel(String name, String password) throws NoUserFoundException;
    void createUserByUserDTOAndType(UserDTO userDTO, String type) throws CantCreateUserException;
    boolean checkUserLogin(String name, String password);
    String isUserFree(UserDTO userDTO);
    void rentEstate(int userID, int estateID) throws NoEstateFoundException, NoUserFoundException;
    String getNotification(String name) throws CantCreateUserException;
    String payRent(String value, int estateID) throws NoEstateFoundException;
}
