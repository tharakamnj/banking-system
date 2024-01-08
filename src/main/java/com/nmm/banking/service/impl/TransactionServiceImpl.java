package com.nmm.banking.service.impl;

import com.nmm.banking.dto.TransactionDto;
import com.nmm.banking.entity.Account;
import com.nmm.banking.entity.Branch;
import com.nmm.banking.entity.Transaction;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.AccountRepository;
import com.nmm.banking.repository.TransactionRepository;
import com.nmm.banking.service.TransactionService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<CommonResponse> makeTransaction(TransactionDto dto) {
        log.info("Start makeTransaction method with AccountDto: " + dto);
        CommonResponse commonResponse = new CommonResponse();

        Account account = accountRepository.findById(dto.getAccount()).get();

        Transaction existingTransaction = transactionRepository.findByAccountIdAndTopByOrderByDateDesc(account.getAccountId());

        Transaction transaction = transactionRepository.save(new Transaction(
                dto.getTransactionId(),
                dto.getType(),
                dto.getTransactionAmount(),
                dto.getType() == "CR" ? existingTransaction.getCurrentAmount() + dto.getTransactionAmount() : existingTransaction.getCurrentAmount() - dto.getTransactionAmount(),
                account,
                new Date(),
                dto.getModifiedBy()
        ));


        commonResponse.setPayload(Collections.singletonList(transaction));
        commonResponse.setStatus(CommonConst.CREATED);
        log.info("End makeTransaction method");
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> findAllTransactionByAccount(int accountId) {
        log.info("Start findAllTransactionByAccount method ");
        CommonResponse commonResponse = new CommonResponse();

        List<Transaction> existingTransactions = transactionRepository.findByAccountId(accountId);
        
        commonResponse.setPayload(Collections.singletonList(existingTransactions));
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        log.info("End findAllTransactionByAccount method");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
