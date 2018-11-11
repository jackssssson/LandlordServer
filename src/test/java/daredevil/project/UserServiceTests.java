package daredevil.project;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Estates;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.models.User;
import daredevil.project.servieces.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.text.ParseException;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    @Mock
    UserRepository userRepository;

    @Mock
    EstatesRepository estatesRepository;

    @InjectMocks
    UserServiceImpl userService;

    List<User> users = new ArrayList<>();
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    {
        users.add(user1 = new User("Denis", "123456", "asdfg@abv.bg", "someIban", "Landlord"));
        users.add(user2 = new User("Jivko", "123458", "qwerty@abv.bg", "someIban2", "Tenant"));
        users.add(user3 = new User("Aleks", "123468", "gfdsa@abv.bg", "someIban3", "Landlord"));
        users.add(user4 = new User("Georgi", "123686", "ytrewq@abv.bg", "someIban4", "Tenant"));
        users.add(user5 = new User("Ivan", "123876", "zaqxsw@abv.bg", "someIban5", "Landlord"));

    }

    List<Estates> estates = new ArrayList<>();
    Estates estates1;
    Estates estates2;
    Estates estates3;

    {
        estates.add(estates1 = new Estates(500, "Denis' flat", "someAddres"));
        estates.add(estates2 = new Estates(750, "Aeks' flat", "someAddres2"));
        estates.add(estates3 = new Estates(1000, "Ivan's flat", "someAddres3"));
    }

    @Test
    public void getUserById_ShouldReturn_UserWithTheSameID() throws NoUserFoundException {
        when(userRepository.getUserById(2)).thenReturn(user2);
        User result = userService.getUserById(2);
        Assert.assertEquals(result, user2);
    }

    @Test
    public void getUserByLoginModel_ShouldReturn_UserWithSameUsernameAndPassword() throws NoUserFoundException {
        user1.setPassword(BCrypt.hashpw(user1.getPassword(), BCrypt.gensalt()));
        when(userRepository.getUserByLoginModel("Denis", "123456")).thenReturn(user1);
        User result = userService.getUserByLoginModel("Denis", "123456");
        Assert.assertEquals(result, user1);
    }

    @Test
    public void createUserByUserDTO_ShouldReturn_StringCreated() throws CantCreateUserException {
        UserDTO userDTO = UserDTO.getFromUser(user3);
        String result = userService.createUserByUserDTOAndType(userDTO, "Landlord");
        Assert.assertEquals(result, "Landlord created.");
    }

    @Test
    public void createUserByUserDTO_ShouldReturn_ErrorStringWrongUserName() throws CantCreateUserException {
        user3.setName("s");
        UserDTO userDTO = UserDTO.getFromUser(user3);
        String result = userService.createUserByUserDTOAndType(userDTO, "Landlord");
        Assert.assertEquals(result, "Username must not be less than 3 symbols!");
    }

    @Test
    public void createUserByUserDTO_ShouldReturn_ErrorStringWrongEmail() throws CantCreateUserException {
        user3.setEmail("dad");
        UserDTO userDTO=UserDTO.getFromUser(user3);
        String result = userService.createUserByUserDTOAndType(userDTO, "Landlord");
        Assert.assertEquals(result, "Invalid Email!");
    }

    @Test
    public void isUserFree_ShouldReturn_returnUserIsFree() {
        user3.setName("Simon");
        UserDTO userDTO = UserDTO.getFromUser(user3);
        when(userRepository.isUserFree(userDTO)).thenReturn("User is free");
        String result = userService.isUserFree(userDTO);
        Assert.assertEquals(result, "User is free");
    }

    @Test
    public void getNotification_ShouldReturn_NoNotification() throws CantCreateUserException, ParseException {
        Set<Estates> estatesSet = new HashSet<>(estates);
        user1.setEstates(estatesSet);
        when(userRepository.getUserByName("Denis")).thenReturn(user1);
        String result = userService.getNotification("Denis");
        Assert.assertEquals(result, "no notification");

    }

    @Test
    public void getNotification_ShouldReturn_Notification() throws CantCreateUserException, ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);
        Date date=c.getTime();
        estates1.setDueDate(date);
        Set<Estates> estatesSet = new HashSet<>(estates);
        user1.setEstates(estatesSet);
        when(userRepository.getUserByName("Denis")).thenReturn(user1);
        String result = userService.getNotification("Denis");
        Assert.assertEquals(result, "You have " + 1 + " estates with due dates in less than 5 days");

    }

    @Test
    public void payRent_ShouldReturn_Message() throws NoEstateFoundException {
        String value = "50";
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        String result = userService.payRent(value, estates1.getId());

        Assert.assertEquals(result, "You paid " + value);
    }

    @Test
    public void rentEstate_ShouldCall_userRepositoryAndEstatesRepository() throws NoEstateFoundException, NoUserFoundException {
        when(userRepository.getUserById(user1.getId())).thenReturn(user1);
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        userService.rentEstate(user1.getId(), estates1.getId());
        verify(estatesRepository).updateEstate(estates1.getId(), estates1);
        verify(userRepository).updateUser(user1.getId(), user1);
    }

    @Test
    public void updateUser_ShouldCall_userRepository(){
        userService.updateUser(user5.getId(), user4);
        verify(userRepository).updateUser(user5.getId(), user4);
    }

    @Test
    public void deleteUser_ShouldCall_userResitory(){
        userService.deleteUser(user4.getId());
        verify(userRepository).deleteUser(user4.getId());
    }

}
