package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.models.Models.LoginModel;
import daredevil.project.models.Estates;
import daredevil.project.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id) throws NoUserFountEsception;
    void updateUser(int id, User user);
    void deleteUser(int id);
    void addEstate(Estates estates);
    List<User> getUsersByType(String type);
    User getUserByName(String name) throws CantCreateUserException;
    Estates getEstateByUserName(String user);
    boolean updateEstate(int id, Estates estates);
    void createBankAccount(BankAccountModel bankAccountModel);
     BankAccountModel getBankAccount(String iban);
    User getUserByLoginModel(String name, String password) throws NoUserFountEsception;
    void createUserByUserDTOAndType(UserDTO userDTO, String type) throws CantCreateUserException;
    boolean checkUserLogin(String name, String password);
    String isUserFree(UserDTO userDTO);
    void rentEstate(int userID, int estateID) throws NoEstateFoundException, NoUserFountEsception;
    String getNotification(int id);
}
