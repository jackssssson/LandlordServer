package daredevil.project.servieces;


import daredevil.project.models.BankAccount;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.repositories.BankAccountRepository;
import daredevil.project.repositories.EstatesRepository;
import daredevil.project.repositories.UserRepository;
import daredevil.project.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BankAccountRepository bankAccountRepository;
    private EstatesRepository estatesRepository;
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository, EstatesRepository estatesRepository, UserTypeRepository userTypeRepository){
        this.userRepository = userRepository;
        this.bankAccountRepository=bankAccountRepository;
        this.estatesRepository=estatesRepository;
        this.userTypeRepository=userTypeRepository;
    }

    @Override
    public void createUser(User user) {
        BankAccount bankAccount=user.getBank_account();
        Estates estates=user.getEstates();

        estatesRepository.createEstate(estates);
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

}
