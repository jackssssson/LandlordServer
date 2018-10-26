package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.Models.BankAccountModel;
import daredevil.project.models.Models.LoginModel;
import daredevil.project.servieces.Base.UserService;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return UserDTO.getFromUser(service.getUserById(id));
    }

    @PostMapping("/addLandlord")
    public String createLandlord(@RequestBody UserDTO userDTO) {
        String message = "";
        try {
            service.createUserByUserDTOandType(userDTO, "Landlord");
            message = "Landlord Created.";
        }catch (CantCreateUserException e) {
            message = "Can't create Landlord";
        } finally {
            return message;
        }

    }

//    @GetMapping("/getUnoccupiedLandlords")
//    public List<UserDTO> getUnoccupiedLandlords() {
//        List<UserDTO> result = new ArrayList<>();
//        service.getUnoccupiedLandlords().stream().forEach(item -> result.add(UserDTO.getFromUser(item)));
//        return result;
//    }

    @PostMapping("/addTenant")
    public String createTenant(@RequestBody UserDTO userDTO) {

        String message="";
        try {
            service.createUserByUserDTOandType(userDTO, "Tenant");
            message="Tenant created";
        } catch (CantCreateUserException e) {
            message="Can't create Tenant.";
        }
        finally {
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
    public BankAccountModel getBankAccount(@PathVariable String iban){
        return service.getBankAccount(iban);
    }

    @PostMapping("/addBankAccount")
    public void addBankAccount(@RequestBody BankAccountModel bankAccountModel){
        service.createBankAccount(bankAccountModel);
    }

    @GetMapping("/checkLogin/{name}/{password}")
    public boolean checkLoginDetails(@PathVariable String name, @PathVariable String password){

        try {
            User user= service.getUserByLoginModel(name, password);
        } catch (NoUserFountEsception noUserFountEsception) {
            return false;
        }
        return true;
    }

    @GetMapping("/getUser/{name}/{password}")
    public UserDTO getUser(@PathVariable String name, @PathVariable String password){
        UserDTO userDTO;
        try {
            userDTO=UserDTO.getFromUser(service.getUserByLoginModel(name, password));
        } catch (NoUserFountEsception noUserFountEsception) {
            System.out.println(noUserFountEsception.getMessage());
            return null;
        }
        return userDTO;
    }
}
