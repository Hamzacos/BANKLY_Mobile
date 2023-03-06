package com.example.app.service;

import com.example.app.Entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction getTransaction(Long id);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    void debit(Double amount);
    void credit(Long walletId, Double amount);

     List<Transaction> showALL();
}
