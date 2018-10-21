package daredevil.project.repositories;

import daredevil.project.models.BankAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public BankAccountRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createBankAccount(BankAccount bankAccount) {

    }

    @Override
    public BankAccount getBankAccountById(int id) {
        return null;
    }

    @Override
    public void updateBankAccount(int id, BankAccount bankAccount) {

    }

    @Override
    public void deleteBankAccount(int id) {

    }
}
