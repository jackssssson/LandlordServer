package daredevil.project.repositories.base;

import daredevil.project.models.UserRating;

public interface UserRatingRepository {
    void createUserRating(UserRating userRating);
    UserRating getUserRatingById(int id);
    void updateUserRating(int id, UserRating userRating);
    void deleteUserRating(int id);
}
