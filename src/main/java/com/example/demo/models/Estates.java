package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "estates")
public class Estates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estateID")
    private int id;

//    @Column(name = "addressID")
//    private int addressId;

    @Column(name = "estatePrice")
    private float price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estates")
    @JsonIgnore
    private Set<User> users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressID")
    private Address addresses;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }
}
