package com.nmm.banking.service;

import com.nmm.banking.dto.AccountDto;
import com.nmm.banking.util.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<CommonResponse> saveAccount(AccountDto dto);

    ResponseEntity<CommonResponse> findAccount(int id);

    ResponseEntity<CommonResponse> findAccountByUser(int userId);

    ResponseEntity<CommonResponse> findAllAccounts();
}
