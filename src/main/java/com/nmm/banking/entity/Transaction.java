package com.nmm.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String type;
    private double transactionAmount;
    private double currentAmount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private Account account;
    private Date date;
    private String modifiedBy;

    public Transaction(String type, double transactionAmount, double currentAmount, Account account, Date date, String modifiedBy) {
        this.type = type;
        this.transactionAmount = transactionAmount;
        this.currentAmount = currentAmount;
        this.account = account;
        this.date = date;
        this.modifiedBy = modifiedBy;
    }
}
