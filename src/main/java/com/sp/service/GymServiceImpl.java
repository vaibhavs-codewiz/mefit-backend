package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.dto.AllGymsResponse;
import com.sp.dto.GetAllUsersOfGym;
import com.sp.dto.GetParticularGym;
import com.sp.dto.GymDto;
import com.sp.dto.GymLoginResponse;
import com.sp.dto.GymReviewResponse;
import com.sp.dto.TrainerDto;
import com.sp.dto.UserLoginResponse;
import com.sp.entity.Gym;
import com.sp.entity.Review;
import com.sp.entity.Trainer;
import com.sp.entity.User;
import com.sp.repo.GymRepository;
import com.sp.repo.ReviewRepository;
import com.sp.repo.TrainerRepository;
import com.sp.utility.Constants;
import com.sp.utility.Response;
import com.sp.utility.Validations;

@Service
public class GymServiceImpl implements GymService {

	@Autowired
	GymRepository gymRepo;
	@Autowired
	TrainerRepository trainerRepo;
	@Autowired
	ReviewRepository reviewRepo;
	
	@Override
	public Response saveGym(GymDto gymDto) {
		
		Gym gym=new Gym();
		gym.setName(gymDto.getName());
		gym.setAddress(gymDto.getAddress());
		
		String contactNo=gymDto.getContactNo();
		if(Validations.isValidContact(contactNo)) {
			gym.setContactNo(contactNo);
		}
		else
		{
			return new Response(Constants.ERROR,"Invalid Contact Number");
		}
		
		String email=gymDto.getEmail();
		if(Validations.isValidEmail(email)) {
			gym.setEmail(email);
		}
		else {
			return new Response(Constants.ERROR,"Invalid Email Address"); 
		}
		gym.setOwnerName(gymDto.getOwnerName());
		gym.setDailyFee(gymDto.getDailyFee());
		gym.setMonthlyFee(gymDto.getMonthlyFee());
		gym.setPassword(gymDto.getPassword());
		Gym savedGym=gymRepo.save(gym);
		if(savedGym!=null) {
			return new Response(Constants.SUCCESS,"Gym Registered Successfully");
		}
		else {
			return new Response(Constants.ERROR,"Failed To Register Gym");
		}
	}
	@Override
	public List<AllGymsResponse> findAllGym() {
		List<Gym>allGyms=gymRepo.findAll();
		List<AllGymsResponse>al=new ArrayList<>();
		for(Gym g:allGyms) {
			AllGymsResponse allGym=new AllGymsResponse();
			allGym.setId(String.valueOf(g.getGymId()));
			allGym.setGymName(String.valueOf(g.getName()));
			allGym.setAddress(String.valueOf(g.getAddress()));
			allGym.setOwnerName(String.valueOf(g.getOwnerName()));
			allGym.setContactNo(String.valueOf(g.getContactNo()));
			allGym.setEmail(String.valueOf(g.getEmail()));
			allGym.setMonthlyFee(String.valueOf(g.getMonthlyFee()));
			allGym.setDailyFee(String.valueOf(g.getDailyFee()));
			al.add(allGym);
		}
		return al;
	}
	@Override
	public GymLoginResponse loginGym(String email, String password) {
	    if(Validations.isValidEmail(email) && Validations.isValidPassword(password)) {
	    	Optional <Gym> gym=gymRepo.findByEmailAndPassword(email.toLowerCase(),password);
	    	if(gym.isPresent()) {
	    		
				GymLoginResponse gymLogin=new GymLoginResponse();
				gymLogin.setResponseCode(Constants.SUCCESS);
				gymLogin.setResponseMessage("Success");
				gymLogin.setGymEmail(gym.get().getEmail());
				gymLogin.setGymId(String.valueOf(gym.get().getGymId()));
				return gymLogin;
			}
			else {
				GymLoginResponse gymLogin=new GymLoginResponse();
				gymLogin.setResponseCode(Constants.ERROR);
			    gymLogin.setResponseMessage("Can't Find User");
				return gymLogin;
			    
			}
			
			}
			else {
				GymLoginResponse gymLogin=new GymLoginResponse();
				gymLogin.setResponseCode(Constants.ERROR);
				gymLogin.setResponseMessage("Invalid Email Or Password");
				return gymLogin;
				
			}
	}
	@Override
	public List<TrainerDto> getAllTrainers(String id) {
		List<Trainer>trainer=trainerRepo.getAllTrainer(Integer.parseInt(id));
		List<TrainerDto>getTrainer=new ArrayList<>();
		for(Trainer t:trainer) {
			TrainerDto trainerDto=new TrainerDto();
			trainerDto.setName(t.getName());
			trainerDto.setEmail(t.getEmail());
			trainerDto.setContactNo(t.getContactNo());
			trainerDto.setAcheivements(t.getAcheivements());
			getTrainer.add(trainerDto);
		}
		return getTrainer;
	}
	@Override
	public List<GetAllUsersOfGym> getAllUsersByGym(String id) {
		List<Object[]>users=gymRepo.getAllUsersByGym(Integer.parseInt(id));
		List<GetAllUsersOfGym> getUsers=new ArrayList<>();
		for(Object[]u:users) {
			GetAllUsersOfGym getAll=new GetAllUsersOfGym();
			getAll.setUserName(String.valueOf(u[0]));
			getAll.setUserEmail(String.valueOf(u[1]));
			getAll.setStartDate(String.valueOf(u[2]));
			getAll.setEndDate(String.valueOf(u[3]));
			getAll.setSubscriptionType(String.valueOf(u[4]));
			getAll.setBookingDays(String.valueOf(u[5]));
			getUsers.add(getAll);
		}
		return getUsers;
	}
	@Override
	public GetParticularGym getGymDetail(String id) {
		Optional<Gym>optGym=gymRepo.findByGymId(Integer.parseInt(id));
		Gym gym;
		if(optGym.isPresent()) {
	     gym=optGym.get();
		}
		else {
			throw new IllegalArgumentException("Gym Not Found");
		}
		List<Review>reviewList=reviewRepo.findAllByGymId(Integer.parseInt(id));
		List<GymReviewResponse>gymReview=new ArrayList<>();
		for(Review r:reviewList) {
			GymReviewResponse gymReviews=new GymReviewResponse();
			gymReviews.setStar(r.getStar());
			gymReviews.setMessage(r.getReviews());
			gymReview.add(gymReviews);
		}
		GetParticularGym getGym=new GetParticularGym();
		getGym.setReviews(gymReview);
		getGym.setAddress(gym.getAddress());
		getGym.setContactNo(gym.getContactNo());
		getGym.setDailyFee(String.valueOf(gym.getDailyFee()));
		getGym.setEmail(gym.getEmail());
		getGym.setGymName(gym.getName());
		getGym.setMonthlyFee(String.valueOf(gym.getMonthlyFee()));
		getGym.setOwnerName(gym.getOwnerName());
		getGym.setId(id);
		
		return getGym;
	}

}
