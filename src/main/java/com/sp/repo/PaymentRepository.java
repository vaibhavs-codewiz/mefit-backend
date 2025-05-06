package com.sp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
