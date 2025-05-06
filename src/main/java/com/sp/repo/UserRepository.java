package com.sp.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional <User> findByEmailAndPassword(String email,String password);
    public Optional<User> findByEmail(String email);
}
