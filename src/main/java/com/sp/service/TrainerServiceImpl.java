package com.sp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.dto.TrainerDto;
import com.sp.entity.Gym;
import com.sp.entity.Trainer;
import com.sp.repo.GymRepository;
import com.sp.repo.TrainerRepository;
import com.sp.utility.Constants;
import com.sp.utility.Response;
import com.sp.utility.Validations;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerRepository trainerRepo;
	
	@Autowired
	GymRepository gymRepo;
	@Override
	public Response save(TrainerDto trainerDto) {
		
		
	    String id=trainerDto.getGymId();
	    Gym gym;
	    if(id!=null) {
	    	Optional<Gym> optGym=gymRepo.findById(Integer.parseInt(id));
	    	
	    	if(optGym.isPresent()) {
	    		gym=optGym.get();
	    	}
	    	else {
	    		return new Response(Constants.ERROR,"Gym Not Found");
	    	}
	    }
	    else { 
	    	return new Response(Constants.ERROR,"Id Can't Be NULL");
	    }
	    
	    Trainer trainer=new Trainer();
	    trainer.setName(trainerDto.getName());
	    trainer.setAcheivements(trainerDto.getAcheivements());
	    String email=trainerDto.getEmail();
	    if(Validations.isValidEmail(email)) {
	    	trainer.setEmail(email);
	    }
	    else {
	    	return new Response(Constants.ERROR,"Invalid Email Address");
	    }
	    
	    String contactNo=trainerDto.getContactNo();
	    if(Validations.isValidContact(contactNo)) {
	    	trainer.setContactNo(contactNo);
	    }
	    else {
	    	return new Response(Constants.ERROR,"Invalid Contact Number");
	    }
	    
	    trainer.setGym(gym);
	    Trainer savedTrainer=trainerRepo.save(trainer);
	    if(savedTrainer!=null) {
	    	return new Response(Constants.SUCCESS,"Trainer Saved Successfully");
	    }
	    else {
	    	return new Response(Constants.ERROR,"Failed To Add Trainer");
	    }
	}

}
