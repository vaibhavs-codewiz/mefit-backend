package com.sp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.UsersSlot;

@Repository
public interface UserSlotRepository extends CrudRepository<UsersSlot, Integer> {

}
