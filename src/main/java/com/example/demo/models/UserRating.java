package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "userratings")
public class UserRating {
//    public UserRating(User userId, int userRating) {
//        this.users = userId;
//        this.userRating = userRating;
//    }

    public UserRating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "userID", nullable = false)
//    private User users;

    @Column(name = "userRating")
    private int userRating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public User getUsers() {
//        return users;
//    }
//
//    public void setUsers(User userId) {
//        this.users = userId;
//    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }
}
