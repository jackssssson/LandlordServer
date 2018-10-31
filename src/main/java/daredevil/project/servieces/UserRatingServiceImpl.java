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
    public String rateUser(int rating, String rated, String rater) {

        if(rating>5||rating<0)
            return "Rating must be between 0 and 5";
        User ratedUser= null;
        try {
            ratedUser = userRepository.getUserByName(rated);
        } catch (CantCreateUserException e) {
            return "No such user";
        }
        for(UserRating ur:ratedUser.getUser_ratings()){
            if(ur.getRater().getName().equals(rater))
                return "You have already rated this user!";
        }

        User ratingUser=null;
        try{
            ratingUser=userRepository.getUserByName(rater);
        }catch (CantCreateUserException e){
            return "No such user";
        }
        UserRating userRating=new UserRating(rating, ratedUser);
        userRating.setRater(ratingUser);
        ratedUser.getUser_ratings().add(userRating);
        userRatingRepository.createUserRating(userRating);
        userRepository.updateUser(ratedUser.getId(), ratedUser);
        return "User rated successfully";
    }

    @Override
    public String getUserRating(String name) {
        return String.valueOf(userRatingRepository.getUserRatingByUserName(name));
    }
}
