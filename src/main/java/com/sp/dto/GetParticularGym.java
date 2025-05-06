package com.sp.dto;

import java.util.List;

import lombok.Data;

@Data
public class GetParticularGym {


	   private String gymName;
	   private String id;
	   private String address;
	   private String ownerName;
	   private String contactNo;
	   private String email;
	   private String monthlyFee;
	   private String dailyFee;
       private List<GymReviewResponse>reviews;
       }
