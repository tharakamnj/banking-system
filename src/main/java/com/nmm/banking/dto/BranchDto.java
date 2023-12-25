package com.nmm.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private int branchId;
    private String name;
    private String code;
    private int userId;
    private String website;
    private String address;
    private String email;
    private String phone;
    private String currency;
}
