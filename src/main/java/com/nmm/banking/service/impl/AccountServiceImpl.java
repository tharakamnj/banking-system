package com.nmm.banking.service.impl;

import com.nmm.banking.dto.AccountDto;
import com.nmm.banking.entity.Account;
import com.nmm.banking.entity.Branch;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.AccountRepository;
import com.nmm.banking.repository.BranchRepository;
import com.nmm.banking.repository.UserRepository;
import com.nmm.banking.service.AccountService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private BranchRepository branchRepository;

    private UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, BranchRepository branchRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<CommonResponse> saveAccount(AccountDto dto) {
        log.info("Start saveAccount method with AccountDto: " + dto);
        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findById(Integer.valueOf(dto.getUserId())).get();
        Branch branch = branchRepository.findByBranchManagerUserId(user.getUserId());

        Account account = accountRepository.save(new Account(
                dto.getAccountId(),
                dto.getAccountNo(),
                dto.getIfscCode(),
                dto.getType(),
                dto.getAvailableBalance(),
                dto.isStatus(),
                user,
                branch,
                dto.getCreatedBy(),
                new Date(),
                dto.getModifiedBy(),
                new Date()));

        user.setAccount(true);
        userRepository.save(user);


        commonResponse.setPayload(Collections.singletonList(account));
        commonResponse.setStatus(1);
        log.info("End saveAccount method");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> findAccount(int id) {
        log.info("Start findAccount method ");
        CommonResponse commonResponse = new CommonResponse();
        Account account = accountRepository.findById(id).get();

        commonResponse.setPayload(Collections.singletonList(account));
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        log.info("End findAccount method");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> findAccountByUser(int userId) {
        log.info("Start findAccount method ");
        CommonResponse commonResponse = new CommonResponse();
        Account account = accountRepository.findAccountByUserUserId(userId);

        commonResponse.setPayload(Collections.singletonList(account));
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        log.info("End findAccount method");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
