package daredevil.project.repositories.base;

import daredevil.project.models.Address;
import daredevil.project.models.UserType;

public interface UserTypeRepository {
    UserType getUserTypeById(int id);
    void createUserType(UserType userType);
    UserType getUserTypeByType(String type);
    void updateUserType(int id, UserType userType);
    void deleteUserType(int id);
}
