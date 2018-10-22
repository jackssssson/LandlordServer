package daredevil.project.servieces;


import daredevil.project.Exceptions.CantCreateAddressException;
import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.*;
import daredevil.project.models.Models.LandlordModel;
import daredevil.project.models.Models.TenantModel;
import daredevil.project.repositories.base.*;
import daredevil.project.servieces.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BankAccountRepository bankAccountRepository;
    private EstatesRepository estatesRepository;
    private AddressRepository addressRepository;
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository, EstatesRepository estatesRepository, UserTypeRepository userTypeRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.estatesRepository = estatesRepository;
        this.userTypeRepository = userTypeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void createUser(User user) throws CantCreateUserException {
        BankAccount bankAccount = user.getBank_account();

        bankAccountRepository.createBankAccount(bankAccount);
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
    public UserType getUserTypeByType(String type) {
        return userTypeRepository.getUserTypeByType(type);
    }

    @Override
    public void addAddress(Address address) throws CantCreateAddressException {
        addressRepository.createAddress(address);
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

    @Override
    public List<User> getUnoccupiedLandlords() {
        return userRepository.getUnoccupiedLandLords();
    }

    @Override
    public void createUserByLandlordModel(LandlordModel landlordModel) throws CantCreateAddressException, CantCreateEstateException, CantCreateUserException {
        Address address = new Address(landlordModel.getCountry(), landlordModel.getCity(), landlordModel.getStreet(), landlordModel.getStreetNumber(), landlordModel.getFloor(), landlordModel.getFlat(), landlordModel.getEntrance());
        addressRepository.createAddress(address);
        Estates estates = new Estates(landlordModel.getPrice(), landlordModel.getEstateName(), address);
        estatesRepository.createEstate(estates);
        UserType userType = userTypeRepository.getUserTypeByType("landlord");
        User user = new User(landlordModel.getUserName(), landlordModel.getUserPassword(), landlordModel.getUserEmail(), userType, estates);
        BankAccount bankAccount = user.getBank_account();

        bankAccountRepository.createBankAccount(bankAccount);
        userRepository.createUser(user);
    }

    @Override
    public void createUserByTenantModel(TenantModel tenantModel) throws CantCreateUserException {
        Estates estates = estatesRepository.getEstateByUserName(tenantModel.getLandlordName());
        estates.setOccupied(true);
        UserType userType = userTypeRepository.getUserTypeByType("tenant");
        User user = new User(tenantModel.getUserName(), tenantModel.getUserPassword(), tenantModel.getUserEmail(), userType, estates);
        BankAccount bankAccount = user.getBank_account();

        bankAccountRepository.createBankAccount(bankAccount);
        estatesRepository.updateEstate(estates.getId(), estates);
        userRepository.createUser(user);
    }


}
