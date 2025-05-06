package com.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.TrainerDto;
import com.sp.service.TrainerService;
import com.sp.utility.Response;

@RestController
@RequestMapping("/Gym/trainer")
public class TrainerController {

	@Autowired
	TrainerService trainerService;
	@PostMapping(value="/save",consumes="application/json",produces="application/json")
	public Response saveTrainer(@RequestBody TrainerDto trainerDto)
	{
		return trainerService.save(trainerDto);
	}
}
