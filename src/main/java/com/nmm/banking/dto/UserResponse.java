package com.nmm.banking.dto;

import com.nmm.banking.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private Account account;
}
