package com.sp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sp.entity.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {

	@Query("select t from Trainer t where t.gym.gymId = :id")
	  List<Trainer> getAllTrainer(@Param ("id")int id);
	
}
