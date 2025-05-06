package com.sp.service;

import java.util.List;

import com.sp.dto.GetUserDetails;
import com.sp.dto.GymSubscriptionRequest;
import com.sp.dto.UserDto;
import com.sp.dto.UserLoginResponse;
import com.sp.dto.UserSubscriptionResponse;
import com.sp.utility.Response;

public interface UserService {

	public Response saveUser(UserDto userDto);

	public UserLoginResponse loginUser(UserDto userDto);
	
	public Response subscribeGym(GymSubscriptionRequest request);

	public List<UserSubscriptionResponse> getAllSubscriptionOfUser(String email);

	public GetUserDetails getUserDetails(String email);
}
