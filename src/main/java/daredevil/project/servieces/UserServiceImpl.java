package daredevil.project.servieces;


import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.*;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.okhttp.HttpRequester;
import daredevil.project.repositories.base.*;
import daredevil.project.servieces.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EstatesRepository estatesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           EstatesRepository estatesRepository) {
        this.userRepository = userRepository;
        this.estatesRepository = estatesRepository;
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
    public String createUserByUserDTOAndType(UserDTO userDTO, String type) throws CantCreateUserException {
        String encryptedPassword=BCrypt.hashpw(userDTO.getUserPassword(), BCrypt.gensalt());
        String result=isUserValid(userDTO);
        if(!result.equals("valid"))
            return result;
        User user = new User(userDTO.getUserName(),
                encryptedPassword,
                userDTO.getUserEmail(),
                userDTO.getUserIban(),
                type);
        userRepository.createUser(user);
        return type+" created.";
    }



    @Override
    public User getUserByLoginModel(String name, String password) throws NoUserFoundException {
        User test=userRepository.getUserByLoginModel(name, password);
        if(BCrypt.checkpw(password, test.getPassword()))
            return userRepository.getUserByLoginModel(name, password);
        else
            throw new NoUserFoundException();
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
            c.add(Calendar.DATE, 5);
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
    private String isUserValid(UserDTO userDTO) {
        if (userDTO == null)
            return "Please fill all fields";
        if (userDTO.getUserName().length() < 3)
            return "Username must not be less than 3 symbols!";
        if (userDTO.getUserPassword().length() < 6)
            return "Password must not be less than 6 symbols!";
        Pattern pattern = Pattern.compile("([a-z0-9][-a-z0-9_\\+\\.]*[a-z0-9])@([a-z0-9][-a-z0-9\\.]*[a-z0-9]\\.(arpa|root|aero|" +
                "biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|" +
                "ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|" +
                "cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|g" +
                "m|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kr|kw|" +
                "ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|" +
                "ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|s" +
                "l|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|um|us|uy|uz|va|vc|ve|vg|vi|" +
                "vn|vu|wf|ws|ye|yt|yu|za|zm|zw)|([0-9]{1,3}\\.{3}[0-9]{1,3}))");
        Matcher matcher = pattern.matcher(userDTO.getUserEmail());
        if (!matcher.matches())
            return "Invalid Email!";
        return "valid";
    }


}
