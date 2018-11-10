package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.User;


public interface UserService {
    User getUserById(int id) throws NoUserFoundException;
    void updateUser(int id, User user);
    void deleteUser(int id);
    User getUserByLoginModel(String name, String password) throws NoUserFoundException;
    String createUserByUserDTOAndType(UserDTO userDTO, String type) throws CantCreateUserException;
    String isUserFree(UserDTO userDTO);
    void rentEstate(int userID, int estateID) throws NoEstateFoundException, NoUserFoundException;
    String getNotification(String name) throws CantCreateUserException;
    String payRent(String value, int estateID) throws NoEstateFoundException;
}
