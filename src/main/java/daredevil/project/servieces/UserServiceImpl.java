package daredevil.project.servieces;


import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.*;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.okhttp.HttpRequester;
import daredevil.project.parser.Base.JsonParser;
import daredevil.project.parser.GsonParser;
import daredevil.project.repositories.base.*;
import daredevil.project.servieces.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EstatesRepository estatesRepository;
    private JsonParser<BankAccountModel> jsonParser;
    private HttpRequester httpRequester;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EstatesRepository estatesRepository, HttpRequester httpRequester) {
        this.userRepository = userRepository;
        this.estatesRepository = estatesRepository;
        this.jsonParser = new GsonParser<>(BankAccountModel.class, BankAccountModel[].class);
        this.httpRequester = httpRequester;
    }

    @Override
    public void createUser(User user) throws CantCreateUserException {
        userRepository.createUser(user);
    }

    @Override
    public User getUserById(int id) throws NoUserFoundException {
        return userRepository.getUserById(id);
    }

    @Override
    public void updateUser(int id, User user) {
        userRepository.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }


    @Override
    public void addEstate(Estates estates) {
        try {
            estatesRepository.createEstate(estates);
        } catch (CantCreateEstateException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<User> getUsersByType(String type) {
        return userRepository.getUsersByType(type);
    }

    @Override
    public User getUserByName(String name) throws CantCreateUserException {
        return userRepository.getUserByName(name);
    }

    @Override
    public Estates getEstateByUserName(String name) {
        return estatesRepository.getEstateByUserName(name);
    }

    @Override
    public boolean updateEstate(int id, Estates estates) {
        estatesRepository.updateEstate(id, estates);
        return false;
    }


    @Override
    public void createUserByUserDTOAndType(UserDTO userDTO, String type) throws CantCreateUserException {
        User user = new User(userDTO.getUserName(), userDTO.getUserPassword(), userDTO.getUserEmail(), userDTO.getUserIban(), type);
        userRepository.createUser(user);
    }

    @Override
    public boolean checkUserLogin(String name, String password) {
        return false;
    }

    @Override
    public void createBankAccount(BankAccountModel bankAccountModel) {
        String body = jsonParser.toJson(bankAccountModel);
        try {
            httpRequester.post("http://78.90.22.72:8080/landlordBank/addBankAccount", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankAccountModel getBankAccount(String iban) {
        BankAccountModel bankAccountModel = null;
        try {
            String json = httpRequester.get("http://78.90.22.72:8080/landlordBank/getBankAccount/" + iban);
            bankAccountModel = jsonParser.fromJson(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bankAccountModel;
    }

    @Override
    public User getUserByLoginModel(String name, String password) throws NoUserFoundException {
        return userRepository.getUserByLoginModel(name, password);
    }

    @Override
    public String isUserFree(UserDTO userDTO) {
        return userRepository.isUserFree(userDTO);
    }

    @Override
    public void rentEstate(int userID, int estateID) throws NoEstateFoundException, NoUserFoundException {
        Estates estates = estatesRepository.getEstateById(estateID);
        User user = userRepository.getUserById(userID);
        user.getEstates().add(estates);
        estates.setOccupied(true);
        estates.setTenant(user);
        estatesRepository.updateEstate(estateID, estates);
        userRepository.updateUser(userID, user);
    }

    @Override
    public String getNotification(String name) throws CantCreateUserException {
        String result;
        try {
            Set<Estates> estates = userRepository.getUserByName(name).getEstates();
            int count = 0;
            Calendar c = Calendar.getInstance();
            Date now = new Date();
            c.add(now.getDay(), 5);
            Date checkDate = c.getTime();
            for (Estates e : estates) {
                if (e.getDueDate().after(now) && e.getDueDate().before(checkDate))
                    count++;
            }
            if (count == 0)
                result = "no notification";
            else
                result = "You have " + count + " estates with due dates in less than 5 days";
        }catch (CantCreateUserException e) {
            throw new CantCreateUserException();
        }
        return result;
    }

    @Override
    public String payRent(String value, int estateID) throws NoEstateFoundException {
        float floatValue;
        try {
            floatValue = Float.valueOf(value);
        }catch (Exception e){
            return "Invalid value.";
        }
        Estates estates=estatesRepository.getEstateById(estateID);
        estates.setPrice(estates.getPrice()-floatValue);
        estatesRepository.updateEstate(estateID, estates);
        return "You paid "+value;

    }

}
