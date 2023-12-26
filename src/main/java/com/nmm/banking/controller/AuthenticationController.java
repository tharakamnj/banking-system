package com.nmm.banking.controller;

import com.nmm.banking.dto.AuthResponse;
import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.security.CustomUserDetailsService;
import com.nmm.banking.security.JwtUtil;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	private UserService userService;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		return userService.createAuthnticationToken(authenticationRequest);
	}

	/**
	 * This method use for create user
	 * @param user
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		return userService.saveUser(user);
	}



}
