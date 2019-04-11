package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface CoctailRepo extends CrudRepository<Coctail, BigInteger> {

    List<Coctail> findByName(String name);

    List<Coctail> findByAuthorIn(Collection<User> users);

    List<Coctail> findByAuthor(User user);

    List<Coctail> findByAuthorAndNameContainingIgnoreCase(User user, String name);

    List<Coctail> findByAuthorInAndNameContainingIgnoreCase(Collection<User> users, String filter);
}