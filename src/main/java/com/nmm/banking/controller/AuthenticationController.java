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

	private CustomUserDetailsService customUserDetailsService;

	public AuthenticationController(UserService userService, CustomUserDetailsService customUserDetailsService) {
		this.userService = userService;
		this.customUserDetailsService = customUserDetailsService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<CommonResponse> generateToken(@RequestBody AuthenticationRequest dto) throws Exception {
		CommonResponse commonResponse = new CommonResponse();
		AuthResponse authResponse = new AuthResponse();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
		}catch (Exception ex){
			throw new Exception("invalid username or password...");
		}
		String token = jwtUtil.generateToken(dto.getUsername());
		UserDto user = customUserDetailsService.userDetails(dto.getUsername());
		authResponse.setToken(token);
		authResponse.setUser(user);

		commonResponse.setPayload(Collections.singletonList(authResponse));
		commonResponse.setStatus(CommonConst.SUCCESS_CODE);
		return new ResponseEntity<>(commonResponse, HttpStatus.OK);

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
