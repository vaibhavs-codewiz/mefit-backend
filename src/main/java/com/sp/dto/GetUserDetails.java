package com.sp.dto;

import lombok.Data;

@Data
public class GetUserDetails {

	private String id;
	private String name;
	private String email;
	private String contactNo;
	private String address;
    private String gender;
    private int height;
    private int weight;
		
}
