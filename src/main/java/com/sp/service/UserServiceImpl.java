package com.sp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.dto.GetUserDetails;
import com.sp.dto.GymSubscriptionRequest;
import com.sp.dto.UserDto;
import com.sp.dto.UserLoginResponse;
import com.sp.dto.UserSubscriptionResponse;
import com.sp.entity.*;
import com.sp.entity.User;
import com.sp.entity.UsersSlot;
import com.sp.repo.GymRepository;
import com.sp.repo.PaymentRepository;
import com.sp.repo.SubscriptionRepository;
import com.sp.repo.UserRepository;
import com.sp.repo.UserSlotRepository;
import com.sp.utility.CommonUtility;
import com.sp.utility.Constants;
import com.sp.utility.Response;
import com.sp.utility.Validations;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GymRepository gymRepo;
	
	@Autowired
	UserSlotRepository userSlotRepo;
	
	@Autowired
	SubscriptionRepository subscriptionRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	private static final String DAILY="DAILY";
	private static final String MONTHLY="MONTHLY";
	
	private String INVALID_PASSWORD="Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one special character."; 
	@Override
	public Response saveUser(UserDto userDto) {
		User user=new User();
		
		String email=userDto.getEmail();
		if(Validations.isValidEmail(email)) {
			user.setEmail(email.toLowerCase());
		}
		else
		{
			return new Response(Constants.ERROR,"Invalid Email Address");
		}
		
		String password=userDto.getPassword();
		if(Validations.isValidPassword(password)) {
			user.setPassword(password);
		}
		else {
			return new Response(Constants.ERROR,INVALID_PASSWORD);
		}
		
		String contactNo=userDto.getContactNo();
		if(Validations.isValidContact(contactNo)) {
			user.setContactNo(contactNo);
		}
		else {
			return new Response(Constants.ERROR,"Invalid Contact Number");
		}
		
		
		user.setAddress(userDto.getAddress());
		user.setName(userDto.getName());
		user.setGender(userDto.getGender());
		user.setHeight(userDto.getHeight());
		user.setWeight(userDto.getWeight());
		
		User savedUser=userRepo.save(user);
		if(savedUser!=null)
		{
			return new Response(Constants.SUCCESS,"User Registered Successfully");
		}
		else
		{
			return new Response(Constants.ERROR,"Failed To Register User");
		}
		   	
	}
	@Override
	public UserLoginResponse loginUser(UserDto userDto) {
		if(Validations.isValidEmail(userDto.getEmail()) && Validations.isValidPassword(userDto.getPassword())) {
		Optional <User> user=userRepo.findByEmailAndPassword(userDto.getEmail().toLowerCase(),userDto.getPassword());
		if(user.isPresent()) {
			UserLoginResponse userLogin=new UserLoginResponse();
			userLogin.setResponseCode(Constants.SUCCESS);
			userLogin.setResponseMessage("Success");
			userLogin.setUserEmail(userDto.getEmail());
			return userLogin;
		}
		else {
			UserLoginResponse userLogin=new UserLoginResponse();
			userLogin.setResponseCode(Constants.ERROR);
			userLogin.setResponseMessage("Can't Find User");
			return userLogin;
		    
		}
		
		}
		else {
			UserLoginResponse userLogin=new UserLoginResponse();
			userLogin.setResponseCode(Constants.ERROR);
			userLogin.setResponseMessage("Invalid Email Or Password");
			return userLogin;
			
		}
	}
	@Override
	public Response subscribeGym(GymSubscriptionRequest request) {
	    if(!Validations.isValidEmail(request.getUserEmail())) {
	    	return new Response(Constants.ERROR,"Invalid User Email");
	    }
	     Optional <User>optUser=userRepo.findByEmail(request.getUserEmail());
	     User user;
	     if(optUser.isPresent()) {
	    	 user=optUser.get();
	     }
	     else {
	    	 return new Response(Constants.ERROR,"User Not Found");
	     }
	     Optional<Gym>optGym=gymRepo.findByGymId(Integer.parseInt(request.getGymId()));
	     Gym gym;
	     if(optGym.isPresent()) {
	    	gym=optGym.get(); 
	     }
	     else {
	    	 return new Response(Constants.ERROR,"Gym Not Found");
	     }
	     
	     List<String> bookingDates=request.getDates();
	     StringBuffer dates=new StringBuffer();
	     for(String s:bookingDates) {
	    	 dates.append(s);
	    	 dates.append("#");
	     }
	     
	     
	     
	     
	     long totalDays=(long)bookingDates.size();
	     double amount=0.0;
	     if(request.getSubscriptionType().equalsIgnoreCase(DAILY)) {
	    	 amount=gym.getDailyFee()*(double)totalDays;
	     }
	     UsersSlot userSlot=new UsersSlot();
	     userSlot.setUser(user);
	     userSlot.setSubscriptionType(request.getSubscriptionType());
	     userSlot.setGym(gym);
	     userSlot.setBookingDays(totalDays);
	     userSlot.setDates(dates.substring(0, dates.length()-1));
	     
	     UsersSlot savedUserSlot=userSlotRepo.save(userSlot);
	     Subscription subs=new Subscription();
	     subs.setUser(user);
	     subs.setGym(gym);
	     subs.setUserSlotBooking(savedUserSlot);
	     
	     Subscription savedSubs=subscriptionRepo.save(subs);
	     
	     Payment pay=new Payment();
	     pay.setUser(user);
	     pay.setGym(gym);
	     pay.setPaymentDate(LocalDate.now());
	     pay.setPaymentAmount(amount);
	     pay.setUserSlot(savedUserSlot);
	      
	     Payment savedPayment=paymentRepo.save(pay);
	     
	     return new Response(Constants.SUCCESS,"Successfull");
	    
	}
	@Override
	public List<UserSubscriptionResponse> getAllSubscriptionOfUser(String email) {
		
		 if(!Validations.isValidEmail(email)) {
		    	throw new IllegalArgumentException("INVALID_EMAIL");
		    }
		 List<UserSubscriptionResponse>userList=new ArrayList<>();
		 
		   List<Object[]>userDetails=gymRepo.getUserDetails(email);
		   
		   for(Object[]obj:userDetails) {
			   UserSubscriptionResponse userSubsRes=new UserSubscriptionResponse();
			   String dbDates=String.valueOf(obj[0]);
			   String []dates=dbDates.split("#");
			   userSubsRes.setBookingDates(Arrays.asList(dates));
			   userSubsRes.setBookingDays(String.valueOf(obj[1]));
			   userSubsRes.setGymId(String.valueOf(obj[2]));
			   userSubsRes.setUserId(String.valueOf(obj[3]));
			   userSubsRes.setUserName(String.valueOf(obj[4]));
			   userSubsRes.setGymName(String.valueOf(obj[5]));
			   userSubsRes.setPaymentAmount(String.valueOf(obj[6]));
			   userSubsRes.setGymAddress(String.valueOf(obj[7]));
			   
			   
			   userList.add(userSubsRes);
		   }
		  
		return userList;
	}
	@Override
	public GetUserDetails getUserDetails(String email) {
		 Optional <User>optUser=userRepo.findByEmail(email);
	     User user=optUser.get();
	     GetUserDetails getDetails=new GetUserDetails();
	     getDetails.setId(String.valueOf(user.getUserId()));
	     getDetails.setName(user.getName());
	     getDetails.setAddress(user.getAddress());
	     getDetails.setEmail(user.getEmail());
	     getDetails.setContactNo(user.getContactNo());
	     getDetails.setGender(user.getGender());
	     getDetails.setHeight(user.getHeight());
	     getDetails.setWeight(user.getWeight());
	     getDetails.setGender(user.getGender());
		return getDetails;
	}
	
   
    
    

}
