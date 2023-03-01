package com.example.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private double balance;

    @Enumerated
    private Currency currency;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private appUser user;
}
