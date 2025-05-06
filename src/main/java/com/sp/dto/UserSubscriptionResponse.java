package com.sp.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserSubscriptionResponse {

	private String userName;
	private String gymName;
	private String gymAddress;
	private String paymentAmount;
	private String bookingDays;
	private String userId;
	private String gymId;
	private List<String>bookingDates;
	
}
