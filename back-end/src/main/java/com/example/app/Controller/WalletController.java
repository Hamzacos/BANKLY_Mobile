package com.example.app.Controller;

import com.example.app.DTO.WalletDTO;
import com.example.app.Entity.Wallet;
import com.example.app.Entity.appUser;
import com.example.app.service.WalletService;
import com.example.app.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;
    private final AccountServiceImpl accountService;

    @PostMapping("/add")
    public Wallet addWallet(@RequestBody Wallet wallet) {
        return walletService.addWallet(wallet);
    }

    @PutMapping("/{wallet_id}")
    public Wallet updateWallet(@PathVariable Long wallet_id, @RequestBody Wallet wallet) {
        return walletService.updateWallet(wallet_id, wallet);
    }

    @GetMapping("/Wallets")
    public List<WalletDTO> getWallet() {
        return walletService.getAll();
    }

    /*@GetMapping("/Wallet/{walletId}")
    public Wallet getOne(@PathVariable Long walletId) {
        return walletService.getOne(walletId);
    }*/

    @GetMapping("/balance/{walletId}")
    public ResponseEntity<Double> checkBalance(@PathVariable Long walletId) {
        Double balance = walletService.checkBalance(walletId);
        return ResponseEntity.ok(balance);
    }


    @GetMapping("/mywallets")
    public List<WalletDTO> getUserWallets() {
        return walletService.showMywallet();
    }

    @GetMapping("/getWalletuser")
    public List<Wallet>  showWaalet(){
        return walletService.getOneWallet();
    }
}
