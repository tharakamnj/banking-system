package com.nmm.banking.dto;

import com.nmm.banking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private int branchId;
    private String bankName;
    private String bankCode;
    private  String bankDesc;
    private  User branchManager;
    private String website;
    private String bankAddress;
    private String email;
    private String phone;
    private String currency;
    private String country;
    private boolean status;

}
