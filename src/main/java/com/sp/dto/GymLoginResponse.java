package com.sp.dto;

import lombok.Data;

@Data
public class GymLoginResponse {

	private String responseCode;
	private String responseMessage;
	private String gymEmail;
    private String gymId;
}
