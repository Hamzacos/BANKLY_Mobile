package com.example.app.service.impl;

import com.example.app.DTO.WalletDTO;
import com.example.app.Entity.Currency;
import com.example.app.Entity.Wallet;
import com.example.app.Reposetry.UserRepository;
import com.example.app.Reposetry.WalletRepository;
import com.example.app.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

   /* public List<Wallet> getAll() {
        return walletRepository.findAll();
    }*/

    public List<WalletDTO> getAll() {
        List<Wallet> wallets = walletRepository.findAll();
        return wallets.stream()
                .map(wallet -> modelMapper.map(wallet, WalletDTO.class))
                .collect(Collectors.toList());
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


    @Override
    public List<WalletDTO> getOne(){
        List<Wallet> wallets = walletRepository.findWalletByUserUsername(currentUser());
        return wallets.stream()
                .map(wallet -> modelMapper.map(wallet,WalletDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Wallet> getOneWallet() {
        return walletRepository.findWalletByUserUsername(currentUser());
    }


    public double checkBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));
        return wallet.getBalance();
    }

    public List<WalletDTO> getWalletsByUserId(Long userId) {
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        List<WalletDTO> walletDTOs = new ArrayList<>();
        for (Wallet wallet : wallets) {
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setId(wallet.getId());
            walletDTO.setBalance(wallet.getBalance());
            walletDTO.setCurrency(wallet.getCurrency());
            walletDTOs.add(walletDTO);
        }
        return walletDTOs;
    }

     @Override
    public List<WalletDTO> showMywallet(){
        List<Wallet> wallets = walletRepository.findWalletByUserUsername(currentUser());
        return wallets.stream()
                .map(wallet -> modelMapper.map(wallet,WalletDTO.class))
                .collect(Collectors.toList());
    }


    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
