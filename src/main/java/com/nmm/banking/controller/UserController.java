package com.nmm.banking.controller;

import com.nmm.banking.service.UserService;
import com.nmm.banking.util.CommonResponse;
import com.nmm.banking.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<CommonResponse> getAllUsersByRole(@PathVariable("role") Role role){
        log.info("Start getAllUsersByRole method");
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = userService.getAllUsersByRole(role);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End getAllUsersByRole method");
        return responseEntity;
    }

    @GetMapping("/branchManager/{branchManagerId}")
    public ResponseEntity<?> getBranchManagerById(@PathVariable("branchManagerId") Integer branchManagerId) {
        log.info("Start getBranchManagerById method");
        CommonResponse commonResponse = new CommonResponse();
        try {
            ResponseEntity<?> bank = userService.getBranchManagerById(branchManagerId);
            commonResponse.setPayload(Collections.singletonList(bank));
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            log.error("Error occurred while calling the getBankByCode Method : " + e.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/activeManagers")
    public ResponseEntity<CommonResponse> getActiveManagers(){
        log.info("Start getActiveManagers method");
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = userService.getActiveManagers();
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End getActiveManagers method");
        return responseEntity;
    }
}
