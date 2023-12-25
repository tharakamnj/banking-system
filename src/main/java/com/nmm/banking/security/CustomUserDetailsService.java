package com.nmm.banking.security;

import com.nmm.banking.dto.UserDto;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = new User();
        try {
            user = userRepository.findUserByUserName(username);
            System.out.println(user);

        }catch (Exception e){
            logger.debug(e.getMessage());
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }

    public UserDto userDetails(String userName){
       User user = userRepository.findUserByUserName(userName);
        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getTitle(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getNic(),
                user.getEmail(),
                user.getMobile(),
                user.getUserName(),
                user.getRole(),
                user.isStatus(),
                user.getCreatedBy(),
                user.getCreatedDate(),
                user.getModifiedBy(),
                user.getModifiedDate()
        );

        return userDto;
    }
}