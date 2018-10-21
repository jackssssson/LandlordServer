package daredevil.project.controller;

import daredevil.project.models.*;
import daredevil.project.models.DTO.LandlordDTO;
import daredevil.project.servieces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id){
        return service.getUserById(id);
    }

    @PostMapping("/addUser")
    public void createUser(@RequestBody LandlordDTO landlordDTO) {
        Address address=new Address(landlordDTO.getCountry(), landlordDTO.getCity(), landlordDTO.getStreet(), landlordDTO.getStreetNumber(), landlordDTO.getFloor(), landlordDTO.getFlat(), landlordDTO.getEntrance());
        Estates estates=new Estates(landlordDTO.getPrice(), address);
        address.setEstates(estates);
        User user=new User(landlordDTO.getUserName(), landlordDTO.getUserPassword(), landlordDTO.getUserEmail())
        String name, String password, String email,  UserType user_types, Estates estates


        service.createUser(user);
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
