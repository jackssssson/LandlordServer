package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int id;

    @Column(name = "userName")
    private String name;

    @Column(name = "userPassword")
    private String password;

    @Column(name = "userEmail")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
    @JsonIgnore
    private Set<UserRating> user_ratings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userTypeID", nullable = false)
    @JsonIgnore
    private UserType user_types;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estateID", nullable = false)
    @JsonIgnore
    private Estates estates;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankAccountID", nullable = false)
    @JsonIgnore
    private BankAccount bank_account;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Messages> recipientMessage;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Messages> senderMessage;

    public User(String name, String password, String email, Set<UserRating> user_ratings, UserType user_types, Estates estates, BankAccount bank_account, Set<Messages> recipientMessage, Set<Messages> senderMessage) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.user_ratings = user_ratings;
        this.user_types = user_types;
        this.estates = estates;
        this.bank_account = bank_account;
        this.recipientMessage = recipientMessage;
        this.senderMessage = senderMessage;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<UserRating> getUser_ratings() {
        return user_ratings;
    }

    public void setUser_ratings(Set<UserRating> user_ratings) {
        this.user_ratings = user_ratings;
    }

    public UserType getUser_types() {
        return user_types;
    }

    public void setUser_types(UserType user_types) {
        this.user_types = user_types;
    }

    public Estates getEstates() {
        return estates;
    }

    public void setEstates(Estates estates) {
        this.estates = estates;
    }


    public BankAccount getBank_account() {
        return bank_account;
    }

    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }


    public Set<Messages> getRecipientMessage() {
        return recipientMessage;
    }

    public void setRecipientMessage(Set<Messages> recipientMessage) {
        this.recipientMessage = recipientMessage;
    }

    public Set<Messages> getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(Set<Messages> senderMessage) {
        this.senderMessage = senderMessage;
    }
}

