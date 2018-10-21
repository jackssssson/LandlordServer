package daredevil.project.servieces;


import daredevil.project.models.*;
import daredevil.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public void createUser(User user) {
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
    public void addAddress(Address address) {
        addressRepository.createAddress(address);
    }

    @Override
    public void addEstate(Estates estates) {
        estatesRepository.createEstate(estates);
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


}
