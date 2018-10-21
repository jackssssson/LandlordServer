package daredevil.project.servieces;


import daredevil.project.models.User;
import daredevil.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return repository.getUserById(id);
    }

    @Override
    public void update(int id, User user) {
        repository.update(id, user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
