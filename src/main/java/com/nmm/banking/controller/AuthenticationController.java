package com.nmm.banking.controller;

import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.security.JwtUtil;
import com.nmm.banking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
	public String generateToken(@RequestBody AuthenticationRequest dto) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUserName(),dto.getPassword()));
		}catch (Exception ex){
			throw new Exception("invalid username or password...");
		}
		return jwtUtil.generateToken(dto.getUserName());
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
