package com.sp.service;

import java.util.List;

import com.sp.dto.AllGymsResponse;
import com.sp.dto.GetAllUsersOfGym;
import com.sp.dto.GetParticularGym;
import com.sp.dto.GymDto;
import com.sp.dto.GymLoginResponse;
import com.sp.dto.GymReviewResponse;
import com.sp.dto.TrainerDto;
import com.sp.utility.Response;

public interface GymService {
  public Response saveGym(GymDto gymDto);

public List<AllGymsResponse> findAllGym();

public GymLoginResponse loginGym(String email, String password);

public List<TrainerDto> getAllTrainers(String id);

public List<GetAllUsersOfGym> getAllUsersByGym(String id);

public GetParticularGym getGymDetail(String id);
}
