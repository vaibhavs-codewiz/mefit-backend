package com.sp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.dto.GymReviewRequest;
import com.sp.entity.Gym;
import com.sp.entity.Review;
import com.sp.entity.User;
import com.sp.repo.GymRepository;
import com.sp.repo.ReviewRepository;
import com.sp.repo.UserRepository;
import com.sp.utility.Constants;
import com.sp.utility.Response;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GymRepository gymRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Override
	public Response saveReview(GymReviewRequest request) {
		Optional <User>optUser=userRepo.findByEmail(request.getUserEmail());
	     User user;
	     if(optUser.isPresent()) {
	    	 user=optUser.get();
	     }
	     else {
	    	 return new Response(Constants.ERROR,"User Not Found");
	     }
	     Optional<Gym>optGym=gymRepo.findByGymId(request.getGymId());
	     Gym gym;
	     if(optGym.isPresent()) {
	    	gym=optGym.get(); 
	     }
	     else {
	    	 return new Response(Constants.ERROR,"Gym Not Found");
	     }
	    Review review=new Review();
	    review.setUser(user);
        review.setGym(gym);
        review.setStar(request.getStar());
        review.setReviews(request.getMessage());
        
        reviewRepo.save(review);
	    return new Response(Constants.SUCCESS,"Review Added Successfully");
	}

}
