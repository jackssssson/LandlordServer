package daredevil.project.repositories;

import daredevil.project.models.Transactions;

public interface TransactionsRepository {
    void createTransaction(Transactions transaction);
    Transactions getTransactionById(int id);
    void updateTransaction(int id, Transactions transaction);
    void deleteTransaction(int id);
}
