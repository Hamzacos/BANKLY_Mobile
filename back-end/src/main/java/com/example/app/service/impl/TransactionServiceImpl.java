package com.example.app.service.impl;

import com.example.app.DTO.WalletDTO;
import com.example.app.Entity.Transaction;
import com.example.app.Entity.Type;
import com.example.app.Entity.Wallet;
import com.example.app.Reposetry.TransactionRepository;
import com.example.app.Reposetry.WalletRepository;
import com.example.app.service.TransactionService;
import com.example.app.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null); }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = getTransaction(id);
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setType(transaction.getType());
        existingTransaction.setDate(transaction.getDate());
        return transactionRepository.save(existingTransaction);
    }
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public void debit(Double amount) {
        List<Wallet> wallets = walletService.getOneWallet();
        if (wallets.isEmpty()) {
            throw new IllegalStateException("Aucun portefeuille n'a été trouvé pour cet utilisateur.");
        }
        Wallet wallet = wallets.get(0);
        double currentBalance = wallet.getBalance();
        if (currentBalance >= amount) {
            Double newBalance = currentBalance - amount;
            Transaction transaction = new Transaction();
            transaction.setAmount(newBalance);
            transaction.setAmountTransaction(amount);
            transaction.setType(Type.Debiter);
            transaction.setWallet(wallet);
            wallet.setBalance(newBalance);
            transactionRepository.save(transaction);
            walletRepository.save(wallet);
        } else {
            throw new IllegalStateException("Votre solde est insuffisant");
        }
    }



    public void credit(Long walletId, Double amount) {
        Wallet wallet = walletRepository.getOne(walletId);
        double newBalance = wallet.getBalance() +amount ;
        wallet.setBalance(newBalance);
        Transaction transaction = new Transaction();
        transaction.setAmount(newBalance);
        transaction.setAmountTransaction(amount);
        transaction.setWallet(wallet);
        transaction.setType(Type.Crediter);
        transactionRepository.save(transaction);
        walletRepository.save(wallet);

    }

    public List<Transaction> showALL(){
        return transactionRepository.findAll();
    }
}
