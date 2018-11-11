package daredevil.project;

import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.MessagesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.MessagesServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MessagesServiceTests {
    @Mock
    MessagesRepository messagesRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EstatesRepository estatesRepository;

    @InjectMocks
    MessagesServiceImpl messagesService;

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
}
