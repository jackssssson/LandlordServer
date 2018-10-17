package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "landlord")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPassword")
    private String userPassword;

   //@Column(name = "userType")
   //private TypeEnum userType;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userRating")
    private String userRating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

   // public TypeEnum getUserType() {
   //     return userType;
   // }
//
   // public void setUserType(TypeEnum userType) {
   //     this.userType = userType;
   // }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}

