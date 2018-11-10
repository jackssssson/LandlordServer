package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoUserFoundException;
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
        try {
            return UserDTO.getFromUser(service.getUserById(id));
        } catch (NoUserFoundException noUserFoundException) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/addLandlord")
    public String createLandlord(@RequestBody UserDTO userDTO) {
        try {
            return service.createUserByUserDTOAndType(userDTO, "Landlord");
        } catch (CantCreateUserException e) {
            return "Can't create Landlord";
        }
    }

    @PostMapping("/addTenant")
    public String createTenant(@RequestBody UserDTO userDTO) {
        try {
            return service.createUserByUserDTOAndType(userDTO, "Tenant");
        } catch (CantCreateUserException e) {
            return "Can't create Tenant.";
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
    public String payRent(@PathVariable int estateID, @PathVariable String value) {
        try {
            return service.payRent(value, estateID);
        } catch (NoEstateFoundException e) {
            return "No estate found.";
        }
    }

}
