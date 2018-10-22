package daredevil.project.controller;

import daredevil.project.models.DTO.TenantModel;
import daredevil.project.servieces.UserService;
import daredevil.project.models.Address;
import daredevil.project.models.DTO.LandlordModel;
import daredevil.project.models.DTO.UserDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.User;
import daredevil.project.models.UserType;
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
    public UserDTO getUserById(@PathVariable int id){
        return UserDTO.getFromUser(service.getUserById(id));
    }

    @PostMapping("/addLandlord")
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

    @GetMapping("/getUnoccupiedLandlords")
    public List<UserDTO> getUnoccupiedLandlords(){
        List<UserDTO> result=new ArrayList<>();
        service.getUnoccupiedLandlords().stream().forEach(item->result.add(UserDTO.getFromUser(item)));
        return result;
    }

    @PostMapping("/addTenant")
    public void createUser(@RequestBody TenantModel tenantModel) {
        Estates estates=service.getEstateByUserName(tenantModel.getLandlordName());
        estates.setOccupied(true);
        int b=5;
        UserType userType=service.getUserTypeByType("tenant");
        User user=new User(tenantModel.getUserName(), tenantModel.getUserPassword(), tenantModel.getUserEmail(), userType, estates);
        service.updateEstate(estates.getId(), estates);

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
