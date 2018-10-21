package daredevil.project.controller;

import daredevil.project.models.*;
import daredevil.project.models.DTO.LandlordModel;
import daredevil.project.servieces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/landlord")
public class LandlordController {
    private final UserService service;

    @Autowired
    public LandlordController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id){
        return service.getUserById(id);
    }

    @PostMapping("/addLandlord/")
    public void createUser(@RequestBody LandlordModel landlordModel) {
        Address address=new Address(landlordModel.getCountry(), landlordModel.getCity(), landlordModel.getStreet(), landlordModel.getStreetNumber(), landlordModel.getFloor(), landlordModel.getFlat(), landlordModel.getEntrance());
        service.addAddress(address);
        Estates estates=new Estates(landlordModel.getPrice(), landlordModel.getEstateName(), address);
        service.addEstate(estates);
        address.setEstates(estates);
        UserType userType=service.getUserTypeByType("landlord");
        User user=new User(landlordModel.getUserName(), landlordModel.getUserPassword(), landlordModel.getUserEmail(), userType, estates);


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
