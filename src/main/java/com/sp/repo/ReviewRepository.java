package com.sp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sp.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

	@Query("select r from Review r where r.gym.gymId = :id")
	List<Review>findAllByGymId(@Param("id")int id);
}
