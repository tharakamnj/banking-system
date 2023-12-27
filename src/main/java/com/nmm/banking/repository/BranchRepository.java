package com.nmm.banking.repository;

import com.nmm.banking.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    Branch findByUserId(int userId);
}
