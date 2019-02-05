package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoctailRepo extends CrudRepository<Coctail, Long> {

    List<Coctail> findByName(String name);

}