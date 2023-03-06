package com.example.app.service;


import com.example.app.DTO.WalletDTO;
import com.example.app.Entity.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {

    List<WalletDTO> getAll();

    Wallet addWallet(Wallet wallet);

    Wallet updateWallet(Long id, Wallet wallet);

    double checkBalance(Long walletId);

    List<WalletDTO> getWalletsByUserId(Long userId);

    List<WalletDTO> showMywallet();

    List<WalletDTO> getOne();

    List<Wallet>   getOneWallet();

}
