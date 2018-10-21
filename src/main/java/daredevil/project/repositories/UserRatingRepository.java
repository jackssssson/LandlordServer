package daredevil.project.repositories;

import daredevil.project.models.UserRating;

public interface UserRatingRepository {
    void createUserRating(UserRating userRating);
    UserRating getUserRatingById(int id);
    void updateUserRating(int id, UserRating userRating);
    void deleteUserRating(int id);
}
