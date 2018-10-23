package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateAddressException;
import daredevil.project.Exceptions.CantCreateEstateException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.Models.TenantModel;
import daredevil.project.servieces.Base.UserService;
import daredevil.project.models.Models.LandlordModel;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String createUser(@RequestBody LandlordModel landlordModel) {
        String message = "";
        try {
            service.createUserByLandlordModel(landlordModel);
            message = "Landlord Created.";
        } catch (CantCreateAddressException e) {
            message = "Can't create Address";
        } catch (CantCreateEstateException e) {
            message = "Can't create Estate";
        } catch (CantCreateUserException e) {
            message = "Can't create Landlord";
        } finally {
            return message;
        }

    }

    @GetMapping("/getUnoccupiedLandlords")
    public List<UserDTO> getUnoccupiedLandlords() {
        List<UserDTO> result = new ArrayList<>();
        service.getUnoccupiedLandlords().stream().forEach(item -> result.add(UserDTO.getFromUser(item)));
        return result;
    }

    @PostMapping("/addTenant")
    public String createUser(@RequestBody TenantModel tenantModel) {

        String message="";
        try {
            service.createUserByTenantModel(tenantModel);
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
}
