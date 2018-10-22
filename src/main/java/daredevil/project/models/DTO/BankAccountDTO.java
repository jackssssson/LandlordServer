package daredevil.project.models.DTO;

import daredevil.project.models.BankAccount;

public class BankAccountDTO {
    private int bankAccountID;
    private float balance;

    public BankAccountDTO() {
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public BankAccountDTO(int bankAccountID, float balance) {
        this.bankAccountID = bankAccountID;
        this.balance = balance;
    }

    public static BankAccountDTO getByBankAccount(BankAccount bankAccount){
        return new BankAccountDTO(bankAccount.getId(), bankAccount.getBalance());
    }
}
