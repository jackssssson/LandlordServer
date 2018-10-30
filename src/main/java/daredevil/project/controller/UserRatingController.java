package daredevil.project.controller;

import daredevil.project.servieces.Base.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userRating")
public class UserRatingController {
    private UserRatingService userRatingService;

    @Autowired
    public UserRatingController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @PostMapping("/rateUser/{rating}/{userName}")
    public String rateUser(@PathVariable int rating, @PathVariable String userName){
        return userRatingService.rateUser(rating, userName);
    }

    @GetMapping("getUserRating/{name}")
    public String getUserRating(@PathVariable String name){
        return userRatingService.getUserRating(name);
    }
}
