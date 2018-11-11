package daredevil.project;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.User;
import daredevil.project.models.UserRating;
import daredevil.project.repositories.base.UserRatingRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.UserRatingServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserRatingServiceTests {
    @Mock
    UserRepository userRepository;

    @Mock
    UserRatingRepository userRatingRepository;

    @InjectMocks
    UserRatingServiceImpl userRatingService;

    List<User> users = new ArrayList<>();
    User user1;
    User user2;

    {
        users.add(user1 = new User("Denis", "123456", "asdfg@abv.bg", "someIban", "Landlord"));
        users.add(user2 = new User("Jivko", "123458", "qwerty@abv.bg", "someIban2", "Tenant"));
    }


    @Test
    public void rateUser_ShouldCall_UserRepositoryAndUserRatingRepository() throws CantCreateUserException {
        when(userRepository.getUserByName(user1.getName())).thenReturn(user1);
        when(userRepository.getUserByName(user2.getName())).thenReturn(user2);
        String result=userRatingService.rateUser(3, user1.getName(), user2.getName());
        Assert.assertEquals(result, "User rated successfully");
    }

    @Test
    public void rateUser_ShouldReturn_InvalidRating() {
        String result=userRatingService.rateUser(8, user1.getName(), user2.getName());
        Assert.assertEquals(result, "Rating must be between 0 and 5");
    }

    @Test
    public void rateUser_ShouldReturn_InvalidUser() throws CantCreateUserException {
        when(userRepository.getUserByName(user1.getName())).thenThrow(new CantCreateUserException());
        String result=userRatingService.rateUser(3, user1.getName(), user2.getName());
        Assert.assertEquals(result, "No such user");
    }

    @Test
    public void rateUser_ShouldReturn_InvalidUser2() throws CantCreateUserException {
        when(userRepository.getUserByName(user1.getName())).thenReturn(user1);
        when(userRepository.getUserByName(user2.getName())).thenThrow(new CantCreateUserException());
        String result=userRatingService.rateUser(3, user1.getName(), user2.getName());
        Assert.assertEquals(result, "No such user");
    }

    @Test
    public void getUserRatingByUserName_ShouldReturn_RatingToString(){
        when(userRatingRepository.getUserRatingByUserName(user1.getName())).thenReturn(3.5);
        String result=userRatingService.getUserRating(user1.getName());
        Assert.assertEquals(result, String.valueOf(3.5));
    }
}
