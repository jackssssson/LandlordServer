package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "estates")
public class Estates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estateID")
    private int id;

    @Column(name = "addressID")
    private int addressId;

    @Column(name = "estatePrice")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
