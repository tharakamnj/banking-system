package com.nmm.banking.service.impl;

import com.nmm.banking.dto.UserDto;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.UserRepository;
import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method use for user registration
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<?> saveUser(UserDto dto) {
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
                dto.getPassword(),
                dto.isStatus(),
                dto.getCreatedBy(),
                new Date(),
                dto.getCreatedBy(),
                new Date(),
                null
        ));
        //commonResponse.setPayload(Collections.singletonList(this.convertToDto(user)));
        commonResponse.setPayload(Collections.singletonList(user));
        commonResponse.setStatus(1);
        log.info("End saveUser method");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }
}
