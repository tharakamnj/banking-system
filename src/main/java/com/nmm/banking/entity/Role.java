package com.nmm.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    private int roleId;
    private String roleName;
    private boolean status;
    private String systemName;
    private Date createdDate;
    private Date updateDate;
}
