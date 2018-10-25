package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateAddressException;
import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.Address;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.models.Models.LandlordModel;
import daredevil.project.models.Models.TenantModel;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.models.UserType;

import java.util.List;

public interface UserService {
    void createUser(User user) throws CantCreateUserException;
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    UserType getUserTypeByType(String type);
    void addAddress(Address address) throws CantCreateAddressException;
    void addEstate(Estates estates);
    List<User> getUsersByType(String type);
    User getUserByName(String name);
    Estates getEstateByUserName(String user);
    boolean updateEstate(int id, Estates estates);
    List<User> getUnoccupiedLandlords();
    void createUserByLandlordModel(LandlordModel landlordModel) throws CantCreateAddressException, CantCreateEstateException, CantCreateUserException;
    void createUserByTenantModel(TenantModel tenantModel) throws CantCreateUserException;
    void createBankAccount(BankAccountModel bankAccountModel);
    public BankAccountModel getBankAccount(String iban);

}
