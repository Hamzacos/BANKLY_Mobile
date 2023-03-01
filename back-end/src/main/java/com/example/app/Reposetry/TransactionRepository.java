package com.example.app.Reposetry;

import com.example.app.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Optional<Transaction> findById(Long aLong);
}
