package daredevil.project.controller;

import daredevil.project.models.User;
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
    public void createUser(@RequestBody User user) {
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
