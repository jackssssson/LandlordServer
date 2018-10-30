package daredevil.project.servieces.Base;

public interface UserRatingService {
    String rateUser(int rating, String name);
    String getUserRating(String name);
}
