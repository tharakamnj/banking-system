package com.nmm.banking.controller;

import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.security.JwtUtil;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<CommonResponse> generateToken(@RequestBody AuthenticationRequest dto) throws Exception {
		CommonResponse commonResponse = new CommonResponse();
		try {
			// Authenticate the user
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		} catch (Exception ex) {
			// Handle authentication failure
			throw new Exception("Invalid username or password...");
		}

		// Generate and return JWT token on successful authentication
		String token = jwtUtil.generateToken(dto.getUsername());
		commonResponse.setPayload(Collections.singletonList(token));
		return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);

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
