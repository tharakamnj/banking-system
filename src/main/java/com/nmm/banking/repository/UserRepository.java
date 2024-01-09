package com.nmm.banking.repository;

import com.nmm.banking.entity.User;
import com.nmm.banking.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

//    User findUserByUserName(String userName);

    List<User> findByRoles(Role role);

    List<User> findByRolesAndStatus(Role role, boolean status);

    @Query(value = "SELECT c FROM User c WHERE c.userName = ?1")
    User findByUserName(String username);

    @Query(value = "SELECT c.userId FROM User c WHERE c.account = ?1")
    Set<Integer> userIdsOfAccountHolders(boolean account);
}
