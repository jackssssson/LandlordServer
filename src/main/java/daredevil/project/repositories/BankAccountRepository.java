package daredevil.project.repositories;

import daredevil.project.models.BankAccount;

public interface BankAccountRepository {
    void createBankAccount(BankAccount bankAccount);
    BankAccount getBankAccountById(int id);
    void updateBankAccount(int id, BankAccount bankAccount);
    void deleteBankAccount(int id);
}
