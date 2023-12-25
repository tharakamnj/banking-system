package com.nmm.banking.service;

import com.nmm.banking.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(UserDto dto);
}
