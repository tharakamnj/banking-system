package com.nmm.banking.dto;

import com.nmm.banking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithAccountResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private int accountId;
    private String accountNo;
    private double currentBalance;

}
