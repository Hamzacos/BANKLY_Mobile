package com.example.app.Controller;


import com.example.app.Entity.Transaction;
import com.example.app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/debit")
    public void debit(@RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        transactionService.debit(amount);
    }
    @PostMapping("/credit")
    public void credit(@RequestParam("walletId") Long walletId, @RequestParam("amount") Double amount) {
        transactionService.credit(walletId, amount);
    }

    @GetMapping("/showAll")
        public List<Transaction> showALL(){
        return transactionService.showALL();
    }


}
