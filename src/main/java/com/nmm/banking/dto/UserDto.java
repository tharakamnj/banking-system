package com.nmm.banking.dto;

import com.nmm.banking.util.Role;
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
    private Role roles;
    private boolean status;

    public UserDto(int userId, String title, String firstName, String middleName, String lastName, String nic,
                   String email, String mobile, String userName, Role role, boolean status, String createdBy,
                   Date createdDate, String modifiedBy, Date modifiedDate) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nic = nic;
        this.email = email;
        this.mobile = mobile;
        this.userName = userName;
        this.role = role;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;
}
