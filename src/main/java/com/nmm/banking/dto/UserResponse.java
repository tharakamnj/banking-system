package com.nmm.banking.dto;

import com.nmm.banking.entity.Account;
import com.nmm.banking.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;

    private String title;
    private String firstName;

    private String middleName;
    private String lastName;

    private String nic;

    private String email;
    private String mobile;

    private String userName;
    private Role roles;
    private boolean status;
    private boolean account;

    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;

    private Account accountDetails;
}
