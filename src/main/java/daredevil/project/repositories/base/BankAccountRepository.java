package daredevil.project.repositories.base;

import daredevil.project.models.BankAccount;

public interface BankAccountRepository {
    void createBankAccount(BankAccount bankAccount);
    BankAccount getBankAccountById(int id);
    void updateBankAccount(int id, BankAccount bankAccount);
    void deleteBankAccount(int id);
}
