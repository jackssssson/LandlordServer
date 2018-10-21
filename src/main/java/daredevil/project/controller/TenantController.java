package daredevil.project.controller;

import daredevil.project.models.*;
import daredevil.project.models.DTO.LandlordModel;
import daredevil.project.models.DTO.TenantModel;
import daredevil.project.servieces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController{
    private final UserService service;

    @Autowired
    public TenantController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id){
        return service.getUserById(id);
    }

    @GetMapping("/getUnoccupiedLandlords")
    public List<User> getUnoccupiedLandlords(){
        return service.getUnoccupiedLandlords();
    }

    @PostMapping("/addTenant/")
    public void createUser(@RequestBody TenantModel tenantModel) {
        Estates estates=service.getEstateByUserName("galibber");
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
