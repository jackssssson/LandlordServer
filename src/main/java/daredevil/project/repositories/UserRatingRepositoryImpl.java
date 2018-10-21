package daredevil.project.repositories;

import daredevil.project.models.UserRating;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRatingRepositoryImpl implements UserRatingRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRatingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUserRating(UserRating userRating) {

    }

    @Override
    public UserRating getUserRatingById(int id) {
        return null;
    }

    @Override
    public void updateUserRating(int id, UserRating userRating) {

    }

    @Override
    public void deleteUserRating(int id) {

    }
}
