package com.nmm.banking.controller;

import com.nmm.banking.dto.AccountDto;
import com.nmm.banking.service.AccountService;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity<CommonResponse> saveAccount(@RequestBody AccountDto dto){
        log.info("Start saveAccount method with AccountDto: " + dto);
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = accountService.saveAccount(dto);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End saveAccount method");
        return responseEntity;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<CommonResponse> findAccount(@PathVariable("id") int id){
        log.info("Start findAccount method with id: " + id);
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = accountService.findAccount(id);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End findAccount method");
        return responseEntity;
    }

    @GetMapping("/account/{userId}")
    public ResponseEntity<CommonResponse> findAccountByUser(@PathVariable("userId") int userId){
        log.info("Start findAccountByUser method with id: " + userId);
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = accountService.findAccountByUser(userId);
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End findAccountByUser method");
        return responseEntity;
    }

    @GetMapping("/accounts")
    public ResponseEntity<CommonResponse> findAllAccounts(){
        log.info("Start findAllAccounts method");
        ResponseEntity<CommonResponse> responseEntity = null;
        CommonResponse commonResponse = new CommonResponse();
        try {
            responseEntity = accountService.findAllAccounts();
        } catch (Exception ex) {
            commonResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            log.error(ex.getMessage());
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        log.info("End findAllAccounts method");
        return responseEntity;
    }

}
