package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface CoctailRepo extends CrudRepository<Coctail, BigInteger> {

    List<Coctail> findByName(String name);

}