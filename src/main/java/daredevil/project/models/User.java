package daredevil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int id;

    @Column(name = "user_Name", nullable = false, unique = true)
    private String name;

    @Column(name = "user_Password", nullable = false)
    private String password;

    @Column(name = "user_Email", nullable = false)
    private String email;

    @Column(name = "iban")
    private String iban;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
    @JsonIgnore
    private Set<UserRating> user_ratings;

    @Column(name = "user_type", nullable = false)
    private String user_type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_estate",
    joinColumns = @JoinColumn(name = "userID"),
    inverseJoinColumns = @JoinColumn(name = "EstateID"))
    private List<Estates> estates;


    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Messages> recipientMessage;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Messages> senderMessage;

    public User(String name, String password, String email, String iban, Set<UserRating> user_ratings, String user_type, List<Estates> estates, Set<Messages> recipientMessage, Set<Messages> senderMessage) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.iban=iban;
        this.user_ratings = user_ratings;
        this.user_type = user_type;
        this.estates = estates;
        this.recipientMessage = recipientMessage;
        this.senderMessage = senderMessage;
    }

    public User(String name, String password, String email, String iban, String user_type, List<Estates> estates) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.iban=iban;
        this.user_ratings = new HashSet<>();
        this.user_type = user_type;
        this.estates = estates;
        this.recipientMessage = new HashSet<>();
        this.senderMessage = new HashSet<>();
    }

    public User(String name, String password, String email, String iban, String user_type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.iban=iban;
        this.user_ratings = new HashSet<>();
        this.user_type = user_type;
        this.estates = new ArrayList<>();
        this.recipientMessage = new HashSet<>();
        this.senderMessage = new HashSet<>();
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Set<UserRating> getUser_ratings() {
        return user_ratings;
    }

    public void setUser_ratings(Set<UserRating> user_ratings) {
        this.user_ratings = user_ratings;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_types) {
        this.user_type = user_types;
    }

    public List<Estates> getEstates() {
        return estates;
    }

    public void setEstates(List<Estates> estates) {
        this.estates = estates;
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

    public void addEstate(Estates estates){
        this.estates.add(estates);
    }
}

