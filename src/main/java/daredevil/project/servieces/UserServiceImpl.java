package daredevil.project.servieces;


import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFountEsception;
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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EstatesRepository estatesRepository;
    private JsonParser<BankAccountModel> jsonParser;
    private HttpRequester httpRequester;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,  EstatesRepository estatesRepository, HttpRequester httpRequester) {
        this.userRepository = userRepository;
        this.estatesRepository = estatesRepository;
        this.jsonParser=new GsonParser<>(BankAccountModel.class, BankAccountModel[].class);
        this.httpRequester=httpRequester;
    }

    @Override
    public void createUser(User user) throws CantCreateUserException {
        userRepository.createUser(user);
    }

    @Override
    public User getUserById(int id) {
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
    public User getUserByName(String name) {
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

//    @Override
//    public List<User> getUnoccupiedLandlords() {
//        return userRepository.getUnoccupiedLandLords();
//    }


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
        String body=jsonParser.toJson(bankAccountModel);
        try {
            httpRequester.post("http://78.90.22.72:8080/landlordBank/addBankAccount", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankAccountModel getBankAccount(String iban){
        BankAccountModel bankAccountModel=null;
        try {
            String json=httpRequester.get("http://78.90.22.72:8080/landlordBank/getBankAccount/"+iban);
            bankAccountModel=jsonParser.fromJson(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bankAccountModel;
    }

    @Override
    public User getUserByLoginModel(String name, String password) throws NoUserFountEsception {
        return userRepository.getUserByLoginModel(name, password);
    }

    @Override
    public String isUserFree(UserDTO userDTO){
        return userRepository.isUserFree(userDTO);
    }

}
