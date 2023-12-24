package com.nmm.banking.dto;

import com.nmm.banking.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;
    private String title;
    private String firstName;

    private String middleName;
    private String lastName;

    private String nic;

    private String email;
    private String mobile;

    private String userName;
    private String password;
    private boolean status;

    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;
    private List<Role> roles;
}
