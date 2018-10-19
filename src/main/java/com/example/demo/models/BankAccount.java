package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankAccountID")
    private int id;

    @Column(name = "ballance")
    private float ballance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBallance() {
        return ballance;
    }

    public void setBallance(float ballance) {
        this.ballance = ballance;
    }
}
