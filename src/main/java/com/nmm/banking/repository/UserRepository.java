package com.nmm.banking.repository;

import com.nmm.banking.entity.User;
import com.nmm.banking.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserName(String userName);

    List<User> findByRole(Role role);
}
