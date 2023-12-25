package com.nmm.banking.service;

import com.nmm.banking.dto.BranchDto;
import com.nmm.banking.util.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface BranchService {
    ResponseEntity<CommonResponse> saveBranch(BranchDto dto);

    ResponseEntity<CommonResponse> findAllBranches();
}
