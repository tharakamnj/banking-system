package com.nmm.banking.service.impl;

import com.nmm.banking.dto.UserDto;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.UserRepository;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import com.nmm.banking.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceImpl(UserRepository userRepository, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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
                    bCryptPasswordEncoder().encode(dto.getPassword()),
                    dto.getRoles(),
                    dto.isStatus(),
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
}
