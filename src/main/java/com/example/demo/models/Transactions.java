package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionID")
    private int id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "time_stamp")
    private Date timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankAccountID", nullable = false)
    @JsonIgnore
    private BankAccount bank_account;

    public Transactions(float amount, Date timestamp, BankAccount bank_account) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.bank_account = bank_account;
    }

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BankAccount getBank_account() {
        return bank_account;
    }

    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }
}
