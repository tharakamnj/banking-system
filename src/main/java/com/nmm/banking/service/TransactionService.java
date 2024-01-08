package com.nmm.banking.service;

import com.nmm.banking.dto.TransactionDto;
import com.nmm.banking.util.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<CommonResponse> makeTransaction(TransactionDto dto);

    ResponseEntity<CommonResponse> findAllTransactionByAccount(int accountId);
}
