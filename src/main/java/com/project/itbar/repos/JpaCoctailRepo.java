package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface JpaCoctailRepo extends JpaRepository <Coctail, BigInteger>{
}
