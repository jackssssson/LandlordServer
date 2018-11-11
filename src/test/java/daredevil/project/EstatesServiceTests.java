package daredevil.project;

import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.models.DTO.EstateDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.EstatesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static daredevil.project.UserServiceTests.initiateDefaultUsers;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstatesServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    EstatesRepository estatesRepository;

    @InjectMocks
    EstatesServiceImpl estatesService;

    List<User> users = new ArrayList<>();
    User user1=new User();
    User user2=new User();
    User user3=new User();
    User user4=new User();
    User user5=new User();
    {
        initiateDefaultUsers(user1,user2,user3,user4,user5,users);
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

    static void initiateDefaultEstates(Estates estates1, Estates estates2, Estates estates3, List<Estates>estates) throws ParseException {
        estates1.setId(1);
        estates1.setPrice(500);
        estates1.setDate("12-12-2018");
        estates1.setOccupied(true);
        estates1.setAddresses("someAddress1");
        estates1.setEstateName("Denis' flat");
        estates2.setId(2);
        estates2.setPrice(750);
        estates2.setDate("12-12-2050");
        estates2.setOccupied(true);
        estates2.setAddresses("someAddress2");
        estates2.setEstateName("Alex's flat");
        estates3.setId(3);
        estates3.setPrice(1000);
        estates3.setDate("18-12-2018");
        estates3.setOccupied(false);
        estates3.setAddresses("someAddress3");
        estates3.setEstateName("Ivan's flat");
        estates.add(estates1);
        estates.add(estates2);
        estates.add(estates3);

    }
    @Test
    public void test(){

    }
//    void createEstate(EstateDTO estateDTO, String name) throws CantCreateEstateException, CantCreateUserException;
    @Test
    public void createEstate_ShouldCall_EstateRepository() throws CantCreateEstateException, CantCreateUserException {
        estates1.setTenant(user2);
        estates1.setLandlord(user1);
        EstateDTO estateDTO=EstateDTO.getFromEstate(estates1);
        Estates estates=new Estates(estateDTO.getPrice(), estateDTO.getEstateName(), estateDTO.getAddress());
        estates.setLandlord(estates1.getLandlord());
        when(userRepository.getUserByName(estates1.getLandlord().getName())).thenReturn(estates1.getLandlord());
        estatesService.createEstate(estateDTO, estates1.getLandlord().getName());
        user1.addEstate(estates);
        verify(userRepository).updateUser(user1.getId(),user1 );

    }
//    void setDueDate(String date, int estateID) throws ParseException, NoEstateFoundException;
    @Test
    public void setDueDate_ShouldCall_EstateRepository() throws NoEstateFoundException, ParseException {
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        estatesService.setDueDate("12-12-2018",estates1.getId());
        estates1.setDate("12-12-2018");
        verify(estatesRepository).updateEstate(estates1.getId(), estates1);
    }
//    Estates getEstateById(int id) throws NoEstateFoundException;
    @Test
    public void getEstateById_ShouldReturn_EstateWithSameId() throws NoEstateFoundException {
        when(estatesRepository.getEstateById(estates3.getId())).thenReturn(estates3);
        Estates result=estatesService.getEstateById(estates3.getId());
        Assert.assertEquals(result, estates3);
    }
//    void setOwed(int id, String owed) throws NoEstateFoundException;
    @Test
    public void setOwed_ShouldCall_EstatesRepository() throws NoEstateFoundException {
        when(estatesRepository.getEstateById(estates3.getId())).thenReturn(estates3);
        float floatOwed=Float.valueOf("5.5");
        estates3.setPrice(floatOwed);
        estatesService.setOwed(estates3.getId(), "5.5");
        verify(estatesRepository).updatePrice(estates3.getId(), estates3);
    }
//    List<Estates> getUnoccupiedEstates();
    @Test
    public void getUnoccupiedEstates_ShouldReturn_unocupiedEstates(){
        List<Estates> estates=this.estates.stream().filter(i->i.isOccupied()==false).collect(Collectors.toList());
        when(estatesRepository.getUnoccupiedEstates()).thenReturn(estates);
        List<Estates> result=estatesService.getUnoccupiedEstates();
        Assert.assertEquals(result, estates);
    }
}
