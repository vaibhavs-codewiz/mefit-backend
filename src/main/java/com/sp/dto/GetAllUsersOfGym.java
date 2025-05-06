package com.sp.dto;

import lombok.Data;

@Data
public class GetAllUsersOfGym {

	private String userName;
	private String userEmail;
	private String startDate;
	private String endDate;
	private String bookingDays;
	private String subscriptionType;
}
