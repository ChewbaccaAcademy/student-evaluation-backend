package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
