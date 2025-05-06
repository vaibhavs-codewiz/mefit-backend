package com.sp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.AllGymsResponse;
import com.sp.dto.GetAllUsersOfGym;
import com.sp.dto.GetParticularGym;
import com.sp.dto.GymDto;
import com.sp.dto.GymLoginResponse;
import com.sp.dto.TrainerDto;
import com.sp.service.GymService;
import com.sp.utility.Response;

@RestController
@RequestMapping("/Gym")
public class GymController {

	@Autowired
	GymService gymService;
	
	@PostMapping(value="/save",consumes="application/json",produces="application/json")
	public Response saveGym(@RequestBody GymDto gymDto) {
		return gymService.saveGym(gymDto);
	}
	
	@GetMapping(produces="application/json")
	public List<AllGymsResponse> getAllGymDetaills() {
	
		return gymService.findAllGym();
	}
	
	@GetMapping(value="/login/{email}/{password}",produces="application/json")
	public GymLoginResponse gymLogin(@PathVariable String email,@PathVariable String password) {
	return 	gymService.loginGym(email,password);
	}
	
	@GetMapping(value="/trainers/{id}",produces="application/json")
	public List<TrainerDto>getAllTrainerOfGym(@PathVariable String id){
		return gymService.getAllTrainers(id);
	}
	
	@GetMapping(value="/users/{id}",produces="application/json")
	public List<GetAllUsersOfGym> getAllUsers(@PathVariable String id){
		return gymService.getAllUsersByGym(id);
	}
	@GetMapping(value="/{id}",produces="application/json")
	public GetParticularGym getGymReview(@PathVariable String id) {
		return gymService.getGymDetail(id);
	}

}
