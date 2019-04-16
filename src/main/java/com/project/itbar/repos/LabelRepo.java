package com.project.itbar.repos;

import com.project.itbar.domain.Label;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface LabelRepo extends CrudRepository<Label, BigInteger> {

    List<Label> findByName(String name);
}
