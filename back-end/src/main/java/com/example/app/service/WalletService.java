package com.example.app.service;


import com.example.app.Entity.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {

    List<Wallet> getAll();

    Wallet addWallet(Wallet wallet);

    Wallet updateWallet(Long id, Wallet wallet);

    Wallet getOne(Long walletId);

    double checkBalance(Long walletId);
}
