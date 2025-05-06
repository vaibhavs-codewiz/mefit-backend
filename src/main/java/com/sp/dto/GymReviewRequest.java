package com.sp.dto;

import lombok.Data;

@Data
public class GymReviewRequest {

	private String userEmail;
	private int gymId;
	private int star;
	private String message;
}
