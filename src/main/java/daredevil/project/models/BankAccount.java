package daredevil.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_AccountID")
    private int id;

    @Column(name = "balance", nullable = false)
    private float balance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bank_account")
    private List<Transactions> transactions;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "bank_account")
    private User users;

    public BankAccount() {
    }

    public BankAccount(float balance, List<Transactions> transactions, User users) {
        this.balance = balance;
        this.transactions = transactions;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
