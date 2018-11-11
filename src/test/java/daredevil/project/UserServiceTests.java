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

import static daredevil.project.EstatesServiceTests.initiateDefaultEstates;
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
    User user1=new User();
    User user2=new User();
    User user3=new User();
    User user4=new User();
    User user5=new User();
    {
        initiateDefaultUsers(user1,user2,user3,user4,user5,users);
    }

    static void initiateDefaultUsers(User user1, User user2, User user3, User user4, User user5, List<User> users){
        user1.setId(1);
        user1.setName("Denis");
        user1.setEmail("asdfg@abv.bg");
        user1.setPassword("123456");
        user1.setUser_type("Landlord");
        user2.setId(2);
        user2.setName("Jivko");
        user2.setEmail("qwerty@abv.bg");
        user2.setPassword("123458");
        user2.setUser_type("Tenant");
        user3.setId(3);
        user3.setName("Aleks");
        user3.setEmail("gfdsa@abv.bg");
        user3.setPassword("123468");
        user3.setUser_type("Landlord");
        user4.setId(4);
        user4.setName("Georgi");
        user4.setEmail("ytrewq@abv.bg");
        user4.setPassword("123686");
        user4.setUser_type("Tenant");
        user5.setId(5);
        user5.setName("Ivan");
        user5.setEmail("zaqxsw@abv.bg");
        user5.setPassword("123876");
        user5.setUser_type("Landlord");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    List<Estates> estates = new ArrayList<>();
    Estates estates1=new Estates();
    Estates estates2=new Estates();
    Estates estates3=new Estates();
    {
        try {
            initiateDefaultEstates(estates1, estates2, estates3, estates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
