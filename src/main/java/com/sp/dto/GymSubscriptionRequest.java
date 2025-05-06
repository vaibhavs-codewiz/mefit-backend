package com.sp.dto;

import java.util.List;

import lombok.Data;
@Data
public class GymSubscriptionRequest {
	private String userEmail;
	private String gymId;
	private String startDate;
	private String endDate;
	private List<String> dates;
	private String subscriptionType;
}
