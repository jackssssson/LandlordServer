package daredevil.project.repositories;

import daredevil.project.models.Address;
import daredevil.project.models.UserType;

public interface UserTypeRepository {
    void createUserType(UserType userType);
    UserType getUserTypeById(int id);
    void updateUserType(int id, UserType userType);
    void deleteUserType(int id);
}
