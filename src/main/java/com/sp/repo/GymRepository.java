package com.sp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sp.entity.Gym;
@Repository
public interface GymRepository extends CrudRepository<Gym, Integer> {
	
 public Optional<Gym> findByGymId(int id);

 String getUserDetails = "SELECT  s.dates, s.booking_days, s.gym_id, s.user_id, u.name, g.name, p.payment_amount ,g.address " +
         "FROM users_slot s " +
         "JOIN user u ON u.user_id = s.user_id " +
         "JOIN gym g ON s.gym_id = g.gym_id " +
         "JOIN payment p ON p.booking_id = s.booking_id " +
         "WHERE u.email = :email";

@Query(value = getUserDetails, nativeQuery = true)
public List<Object[]> getUserDetails(@Param("email") String email);


public List<Gym>findAll();


public Optional<Gym> findByEmailAndPassword(String email, String password);


String getAllUserDetailsByGym=""
		+ " select "
		+ " u.name,"
		+ " u.email,"
		+ " s.start_date,"
		+ " s.end_date,"
		+ " s.subscription_type,"
		+ " s.booking_days"
		+ " from users_slot s "
		+ " inner join user u on u.user_id=s.user_id "
		+ " where s.gym_id = :id ";

 @Query(value= getAllUserDetailsByGym, nativeQuery=true)
 public List<Object[]>getAllUsersByGym(@Param ("id") int id); 
}
