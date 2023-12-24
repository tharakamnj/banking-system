package com.nmm.banking.repository;

import com.nmm.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserName(String userName);
}
