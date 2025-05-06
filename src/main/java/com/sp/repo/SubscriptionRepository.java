package com.sp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

}
