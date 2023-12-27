package com.nmm.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int branchId;
    private String bankName;
    private String bankCode;
    private  String bankDesc;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User branchManager;
    private String website;
    private String bankAddress;
    private String email;
    private String phone;
    private String currency;
    private String country;
    private boolean status;





}
