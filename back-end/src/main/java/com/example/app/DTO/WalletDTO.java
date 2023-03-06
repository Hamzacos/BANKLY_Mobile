package com.example.app.DTO;

import com.example.app.Entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {

    private Long id;
    private double balance;
    private Currency currency;
    private Long userId;

}
