package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_types")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTypeID")
    private int id;

    @Column(name = "userType")
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user_types")
    @JsonIgnore
    private List<User> users;

    public UserType(String type, List<User> users) {
        this.type = type;
        this.users = users;
    }

    public UserType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
