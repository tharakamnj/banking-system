package com.nmm.banking.service.impl;

import com.nmm.banking.dto.AuthResponse;
import com.nmm.banking.dto.AuthenticationRequest;
import com.nmm.banking.dto.UserDto;
import com.nmm.banking.dto.UserWithAccountResponse;
import com.nmm.banking.entity.Account;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.AccountRepository;
import com.nmm.banking.repository.UserRepository;
import com.nmm.banking.security.CustomUserDetailsService;
import com.nmm.banking.security.JwtUtil;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import com.nmm.banking.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    private CustomUserDetailsService customUserDetailsService;

    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceImpl(UserRepository userRepository, EmailServiceImpl emailService, CustomUserDetailsService customUserDetailsService, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.customUserDetailsService = customUserDetailsService;
        this.accountRepository = accountRepository;
    }

    /**
     * This method use for user registration
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> saveUser(UserDto dto) {
        log.info("Start saveUser method with UserDTO: " + dto);
        CommonResponse commonResponse = new CommonResponse();
        /*if (isUserByUserName(dto.getUserName())) {
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList("username already exist."));
            return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
        }*/

        User user = userRepository.save(new User(
                    dto.getUserId(),
                    dto.getTitle(),
                    dto.getFirstName(),
                    dto.getMiddleName(),
                    dto.getLastName(),
                    dto.getNic(),
                    dto.getEmail(),
                    dto.getMobile(),
                    dto.getUserName(),
                    encoder().encode(dto.getPassword()),
                    dto.getRoles(),
                    dto.isStatus(),
                    false,
                    dto.getCreatedBy(),
                    new Date(),
                    dto.getModifiedBy(),
                    new Date()
        ));

        //send an email
        emailService.sendEmail(user.getEmail(),null,
                "registration successfully.");

        //commonResponse.setPayload(Collections.singletonList(this.convertToDto(user)));
        commonResponse.setPayload(Collections.singletonList(user));
        commonResponse.setStatus(1);
        log.info("End saveUser method");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> getAllUsersByRole(Role role) {
        log.info("Start getAllUsersByRole method");
        CommonResponse commonResponse = new CommonResponse();
        List<User> users = userRepository.findByRoles(role);
        if (users.isEmpty()){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found users"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(users));
        log.info("End getAllUsersByRole method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBranchManagerById(Integer branchManagerId) {
        log.info("Start getBranchManagerById method");
        CommonResponse commonResponse = new CommonResponse();
        User user = userRepository.findById(branchManagerId).get();
        if (user==null){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found users"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(user));
        log.info("End getBranchManagerById method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> createAuthnticationToken(AuthenticationRequest authenticationRequest)
        throws BadCredentialsException {
            log.info("Start createAuthenticationToken method");
            CommonResponse commonResponse = new CommonResponse();
        AuthResponse response = new AuthResponse();
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(), authenticationRequest.getPassword()));

            }
            catch (Exception e){
                commonResponse.setErrorMessages(Collections.singletonList("Invalid login credentials"));
                commonResponse.setStatus(CommonConst.UNAUTHORIZED);
                return new ResponseEntity<>(commonResponse, HttpStatus.UNAUTHORIZED);
            }

            UserDetails userdetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
            String token = jwtUtil.generateToken(userdetails);

            UserDto user = customUserDetailsService.userDetails(userdetails.getUsername());
            response.setToken(token);
            response.setUser(user);
            commonResponse.setPayload(Collections.singletonList(response));
            log.info("End createAuthenticationToken method");
            return ResponseEntity.ok(commonResponse);
        }

    @Override
    public ResponseEntity<CommonResponse> getActiveManagers() {
        log.info("Start getActiveManagers method");
        CommonResponse commonResponse = new CommonResponse();
        List<User> users = userRepository.findByRolesAndStatus(Role.MANAGER,true);
        if (users.isEmpty()){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found managers"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(users));
        log.info("End getActiveManagers method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> getCustomersWithAccount() {
        log.info("Start getCustomersWithAccount method");
        CommonResponse commonResponse = new CommonResponse();
        List<UserWithAccountResponse> userWithAccountResponseList = new ArrayList<>();
        Set<Integer> ids = userRepository.userIdsOfAccountHolders(true);

        if (ids.isEmpty()){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found customers"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }

        for (int userId:ids) {
            Account accounts = accountRepository.findAccountByUser(userId);

            UserWithAccountResponse userResponse = new UserWithAccountResponse(
                    accounts.getUser().getUserName(),
                    accounts.getUser().getFirstName(),
                    accounts.getUser().getLastName(),
                    accounts.getUser().getEmail(),
                    accounts.getAccountId(),
                    accounts.getAccountNo(),
                    accounts.getAvailableBalance()
            );
            userWithAccountResponseList.add(userResponse);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(userWithAccountResponseList));
        log.info("End getCustomersWithAccount method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> getUserById(int id) {
        log.info("Start getUserById method");
        CommonResponse commonResponse = new CommonResponse();
        User user = userRepository.findById(id).get();
        if (user==null){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found users"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(user));
        log.info("End getUserById method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }
}
