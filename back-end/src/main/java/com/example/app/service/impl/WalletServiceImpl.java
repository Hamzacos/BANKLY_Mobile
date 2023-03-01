package com.example.app.service.impl;

import com.example.app.Entity.Currency;
import com.example.app.Entity.Wallet;
import com.example.app.Reposetry.UserRepository;
import com.example.app.Reposetry.WalletRepository;
import com.example.app.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }

    public Wallet updateWallet(Long id, Wallet wallet) {
        Wallet walletWithId= walletRepository.findById(id).orElse(null);
        if(walletWithId != null ){
            walletWithId.setBalance(wallet.getBalance());
            walletWithId.setCurrency(Currency.USD);
            return walletWithId;
        }else{
            throw new IllegalStateException("wallet cannot be found");
        }
    }

    public Wallet addWallet(Wallet wallet) {
        wallet.setCurrency(Currency.USD);
        wallet.setUser(userRepository.findByUsername("gavi"));
        return walletRepository.save(wallet);
    }


    public Wallet getOne(Long walletId){
        return walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));
    }


    public double checkBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));
        return wallet.getBalance();
    }
}
