package daredevil.project.repositories;

import daredevil.project.models.UserType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public UserTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUserType(UserType userType) {

    }

    @Override
    public UserType getUserTypeById(int id) {
        return null;
    }

    @Override
    public void updateUserType(int id, UserType userType) {

    }

    @Override
    public void deleteUserType(int id) {

    }
}
