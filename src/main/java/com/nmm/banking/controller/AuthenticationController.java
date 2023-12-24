package com.nmm.banking.controller;


//import com.nmm.banking.config.CustomUserDetailsService;
import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	/*@Autowired
	private AuthenticationManager authenticationManager;*/

	/*@Autowired
	private CustomUserDetailsService customUserDetailsService;*/

	@Autowired
	private UserService userService;

	/**
	 * Generate authentication token based on user credentials
	 * @param authenticationRequest
	 * @return AuthenticationResponse
	 */
	/*@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		return userService.createAuthnticationToken(authenticationRequest);
	}*/

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
