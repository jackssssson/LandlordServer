package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.User;
import daredevil.project.models.UserRating;
import daredevil.project.repositories.base.UserRatingRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.Base.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatingServiceImpl implements UserRatingService {
    private UserRepository userRepository;
    private UserRatingRepository userRatingRepository;

    @Autowired
    public UserRatingServiceImpl(UserRepository userRepository, UserRatingRepository userRatingRepository) {
        this.userRepository = userRepository;
        this.userRatingRepository = userRatingRepository;
    }

    @Override
    public String rateUser(int rating, String name) {

        if(rating>5||rating<0)
            return "Rating must be between 0 and 5";
        User user= null;
        try {
            user = userRepository.getUserByName(name);
        } catch (CantCreateUserException e) {
            return "No such user";
        }

        UserRating userRating=new UserRating(rating, user);
        user.getUser_ratings().add(userRating);
        userRatingRepository.createUserRating(userRating);
        userRepository.updateUser(user.getId(), user);
        return "User rated successfully";
    }

    @Override
    public String getUserRating(String name) {
        return String.valueOf(userRatingRepository.getUserRatingByUserName(name));
    }
}
