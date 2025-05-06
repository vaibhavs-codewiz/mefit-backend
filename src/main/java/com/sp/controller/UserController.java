package com.sp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.GetUserDetails;
import com.sp.dto.GymReviewRequest;
import com.sp.dto.GymSubscriptionRequest;
import com.sp.dto.UserDto;
import com.sp.dto.UserLoginResponse;
import com.sp.dto.UserSubscriptionResponse;
import com.sp.service.ReviewService;
import com.sp.service.UserService;
import com.sp.utility.Response;

@RestController
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	UserService userService;
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping(value="/save",consumes="application/json",produces="application/json")
    public Response saveUser(@RequestBody UserDto userDto)
    {
		return userService.saveUser(userDto);
    }
	@GetMapping(value="/login",consumes="application/json",produces="application/json")
	public UserLoginResponse loginUser(@RequestBody UserDto userDto)
	{
		return userService.loginUser(userDto);
	}

	@PostMapping(value="/gym/subscribe",consumes="application/json",produces="application/json")
	public Response subscribeGym(@RequestBody GymSubscriptionRequest gymSbs) {
		return userService.subscribeGym(gymSbs);
	}
	
	@GetMapping(value="/subscriptions/{email}",produces="application/json")
	public List<UserSubscriptionResponse> getAllSubscriptionOfUser(@PathVariable String email){
		
		return userService.getAllSubscriptionOfUser(email);
	}
	
	@GetMapping(value="/details/{email}",produces="application/json")
	public GetUserDetails getDetails(@PathVariable String email)
	{
		return userService.getUserDetails(email);
	}
	
	@PostMapping(value="/review/save",consumes="application/json",produces="application/json")
	public Response postReview(@RequestBody GymReviewRequest request) {
	    return reviewService.saveReview(request);
	}
}
