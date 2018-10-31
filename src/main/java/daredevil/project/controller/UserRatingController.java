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

    @PutMapping("/rateUser/{rating}/{ratedName}/{raterName}")
    public String rateUser(@PathVariable int rating, @PathVariable String ratedName, @PathVariable String raterName){
        return userRatingService.rateUser(rating, ratedName, raterName);
    }

    @GetMapping("getUserRating/{name}")
    public String getUserRating(@PathVariable String name){
        return userRatingService.getUserRating(name);
    }
}
