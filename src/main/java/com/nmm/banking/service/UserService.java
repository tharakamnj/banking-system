package com.nmm.banking.service;

import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.util.CommonResponse;
import com.nmm.banking.util.Role;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(UserDto dto);

    ResponseEntity<CommonResponse> getAllUsersByRole(Role role);

    ResponseEntity<?> getBranchManagerById(Integer branchManagerId);

    ResponseEntity<CommonResponse> createAuthnticationToken(AuthenticationRequest authenticationRequest);

    ResponseEntity<CommonResponse> getActiveManagers();

    ResponseEntity<CommonResponse> getCustomersWithAccount();

    ResponseEntity<CommonResponse> getUserById(int id);

    ResponseEntity<CommonResponse> getCustomerAccount();
}
