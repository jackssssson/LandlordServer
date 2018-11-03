package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.servieces.Base.UserService;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getUser/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        try {
            return UserDTO.getFromUser(service.getUserById(id));
        } catch (NoUserFoundException noUserFoundException) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/addLandlord")
    public String createLandlord(@RequestBody UserDTO userDTO) {
        String message = isUserValid(userDTO);
        if (!message.equals("valid"))
            return message;
        try {
            service.createUserByUserDTOAndType(userDTO, "Landlord");
            message = "Landlord created.";
        } catch (CantCreateUserException e) {
            message = "Can't create Landlord";
        } finally {
            return message;
        }

    }

    @PostMapping("/addTenant")
    public String createTenant(@RequestBody UserDTO userDTO) {
        String message = isUserValid(userDTO);
        if (!message.equals("valid"))
            return message;
        try {
            service.createUserByUserDTOAndType(userDTO, "Tenant");
            message = "Tenant created.";
        } catch (CantCreateUserException e) {
            message = "Can't create Tenant.";
        } finally {
            return message;
        }

    }

    @PutMapping("/updateUser/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        service.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @GetMapping("/getBankAccount/{iban}")
    public BankAccountModel getBankAccount(@PathVariable String iban) {
        return service.getBankAccount(iban);
    }

    @PostMapping("/addBankAccount")
    public void addBankAccount(@RequestBody BankAccountModel bankAccountModel) {
        service.createBankAccount(bankAccountModel);
    }

    @GetMapping("/checkLogin/{name}/{password}")
    public boolean checkLoginDetails(@PathVariable String name, @PathVariable String password) {

        try {
            User user = service.getUserByLoginModel(name, password);
        } catch (NoUserFoundException noUserFoundException) {
            return false;
        }
        return true;
    }

    @GetMapping("/getUser/{name}/{password}")
    public UserDTO getUser(@PathVariable String name, @PathVariable String password) {
        UserDTO userDTO;
        try {
            User user = service.getUserByLoginModel(name, password);
            userDTO = UserDTO.getFromUser(service.getUserByLoginModel(name, password));
        } catch (NoUserFoundException noUserFoundException) {
            System.out.println(noUserFoundException.getMessage());
            return null;
        }
        return userDTO;
    }

    @PostMapping("/isUserFree")
    public String isUserFree(@RequestBody UserDTO userDTO) {
        return service.isUserFree(userDTO);
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

    @PutMapping("/rentEstate/{userID}/{estateID}")
    public String rentEstate(@PathVariable int userID, @PathVariable int estateID) {
        try {
            service.rentEstate(userID, estateID);
        } catch (NoEstateFoundException e) {
            return "No Estate found!";
        } catch (NoUserFoundException noUserFoundException) {
            return "No User found!";
        }
        return "Estate rented successfully";
    }

    @PutMapping("/getNotification/{userName}")
    public String getNotification(@PathVariable String userName) {
        try {
            return service.getNotification(userName);
        } catch (CantCreateUserException e) {
            return "No user found.";
        }
    }

    @PutMapping("/payRent/{estateID}/{value}")
    public String payRent(@PathVariable int estateID, @PathVariable String value){
        try {
            return service.payRent(value, estateID);
        } catch (NoEstateFoundException e) {
            return "No estate found.";
        }
    }

}
