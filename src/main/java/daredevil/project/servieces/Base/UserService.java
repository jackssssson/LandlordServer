package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.models.Models.LoginModel;
import daredevil.project.models.Estates;
import daredevil.project.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    void addEstate(Estates estates);
    List<User> getUsersByType(String type);
    User getUserByName(String name);
    Estates getEstateByUserName(String user);
    boolean updateEstate(int id, Estates estates);
    //List<User> getUnoccupiedLandlords();
    void createBankAccount(BankAccountModel bankAccountModel);
    public BankAccountModel getBankAccount(String iban);
    User getUserByLoginModel(String name, String password) throws NoUserFountEsception;
    public void createUserByUserDTOandType(UserDTO userDTO, String type) throws CantCreateUserException;
    boolean checkUserLogin(String name, String password);

}
