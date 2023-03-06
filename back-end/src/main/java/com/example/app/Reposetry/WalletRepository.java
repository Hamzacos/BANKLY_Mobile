package com.example.app.Reposetry;

import com.example.app.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findById(Long aLong);

    List<Wallet> findByUserId(Long userId);

    List<Wallet> findWalletByUserUsername(String username);
}
