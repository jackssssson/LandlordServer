package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userType")
    private String userType;

    @Column(name = "userEmail")
    private String userEmail;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
//    private List<UserRating> userRatings;
//
//    public List<UserRating> getUserRatings() {
//        return userRatings;
//    }
//
//    public void setUserRatings(List<UserRating> userRatings) {
//        this.userRatings = userRatings;
//    }
    @Formula("(select avg(u.userRating) from userratings u where u.userID=userID)")
    private double userRating;

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

//    public List<UserRating> getUserRatings() {
//        return userRatings;
//    }
//
//    public void setUserRatings(List<UserRating> userRatings) {
//        this.userRatings = userRatings;
//    }
}

