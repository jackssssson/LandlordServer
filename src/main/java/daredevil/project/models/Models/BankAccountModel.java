package daredevil.project.models.Models;


public class BankAccountModel {
    private String iban;
    private float balance;

    public BankAccountModel() {
    }

    public BankAccountModel(String iban, float balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
