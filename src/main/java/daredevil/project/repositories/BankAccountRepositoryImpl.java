package daredevil.project.repositories;

import daredevil.project.models.BankAccount;
import org.hibernate.Session;
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
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(bankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "bankAccount");
            throw new RuntimeException(e);
        }
    }

    @Override
    public BankAccount getBankAccountById(int id) {
        BankAccount result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(BankAccount.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateBankAccount(int id, BankAccount bankAccount) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            BankAccount bankAccountToChange = session.get(BankAccount.class, id);

            bankAccountToChange.setBalance(bankAccount.getBalance());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBankAccount(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getBankAccountById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
