package daredevil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estates")
public class Estates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estateID")
    private int id;

    @Column(name = "estate_Price", nullable = false)
    private float price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estates")
    @JsonIgnore
    private Set<User> users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressID", nullable = false)
    private Address addresses;

    public Estates(float price, Set<User> users, Address addresses) {
        this.price = price;
        this.users = users;
        this.addresses = addresses;
    }

    public Estates(float price, Address addresses) {
        this.price = price;
        this.users = new HashSet<>();
        this.addresses = addresses;
    }

    public Estates() {
    }

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
