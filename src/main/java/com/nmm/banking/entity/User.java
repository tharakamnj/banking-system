package com.nmm.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Role_id"))
    private List<Role> roles;

}
