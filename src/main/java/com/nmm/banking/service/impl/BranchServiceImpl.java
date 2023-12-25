package com.nmm.banking.service.impl;

import com.nmm.banking.dto.BranchDto;
import com.nmm.banking.entity.Branch;
import com.nmm.banking.entity.User;
import com.nmm.banking.repository.BranchRepository;
import com.nmm.banking.repository.UserRepository;
import com.nmm.banking.service.BranchService;
import com.nmm.banking.util.CommonConst;
import com.nmm.banking.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;

    private UserRepository userRepository;

    public BranchServiceImpl(BranchRepository branchRepository, UserRepository userRepository) {
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<CommonResponse> saveBranch(BranchDto dto) {
        log.info("Start saveBranch method with BranchDto: " + dto);
        CommonResponse commonResponse = new CommonResponse();

        User user = userRepository.findById(dto.getUserId()).get();

        Branch branch = branchRepository.save(
                new Branch(
                        dto.getBranchId(),
                        dto.getName(),
                        dto.getCode(),
                        user,
                        dto.getWebsite(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getCurrency()
                ));


        commonResponse.setPayload(Collections.singletonList(branch));
        commonResponse.setStatus(1);
        log.info("End saveBranch method");
        return new ResponseEntity<>(commonResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> findAllBranches() {
        log.info("Start findAllBranches method");
        CommonResponse commonResponse = new CommonResponse();
        List<Branch> branches = branchRepository.findAll();
        if (branches.isEmpty()){
            commonResponse.setStatus(CommonConst.NOT_FOUND_RECORD);
            commonResponse.setErrorMessages(Collections.singletonList("Not found branches"));
            return new ResponseEntity<>(commonResponse,HttpStatus.NOT_FOUND);
        }
        commonResponse.setStatus(CommonConst.SUCCESS_CODE);
        commonResponse.setPayload(Collections.singletonList(branches));
        log.info("End findAllBranches method");
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


}
