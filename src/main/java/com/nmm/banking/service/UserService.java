package com.nmm.banking.service;

import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.util.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(UserDto dto);

    //ResponseEntity<CommonResponse>  createAuthnticationToken(AuthenticationRequest authenticationRequest);
}
