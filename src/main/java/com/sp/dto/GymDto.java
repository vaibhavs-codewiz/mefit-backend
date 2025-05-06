package com.sp.dto;

import lombok.Data;

@Data
public class GymDto {
	
	private String name;
    private String address;
    private String ownerName;
    private String contactNo;
    private String email;
    private double monthlyFee;
    private double dailyFee;
    private String password;

}
