package com.project.itbar.repos;

import com.project.itbar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepo extends JpaRepository<User, BigInteger> {
    User findByUsername(String username);
}
