package daredevil.project.servieces;

import daredevil.project.models.Address;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.models.UserType;

import java.util.List;

public interface UserService {
    void createUser(User user);
    User getUserById(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    UserType getUserTypeByType(String type);
    void addAddress(Address address);
    void addEstate(Estates estates);
    List<User> getUsersByType(String type);
    User getUserByName(String name);
    Estates getEstateByUserName(String user);
    boolean updateEstate(int id, Estates estates);
    List<User> getUnoccupiedLandlords();
}
