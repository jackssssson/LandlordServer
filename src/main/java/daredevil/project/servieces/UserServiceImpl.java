package daredevil.project.servieces;


import daredevil.project.models.User;
import daredevil.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public void createUser(User user) {
        repository.createUser(user);
    }

    @Override
    public User getUserById(int id) {
        return repository.getUserById(id);
    }

    @Override
    public void updateUser(int id, User user) {
        repository.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteUser(id);
    }
}
