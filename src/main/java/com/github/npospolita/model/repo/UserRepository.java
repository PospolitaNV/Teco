package com.github.npospolita.model.repo;

import com.github.npospolita.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 *
 * @author NPospolita
 * @since 03.12.2017
 */
public interface UserRepository extends CrudRepository<User, String> {
}
