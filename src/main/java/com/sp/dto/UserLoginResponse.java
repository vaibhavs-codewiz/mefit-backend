package com.sp.dto;

import com.sp.utility.Response;
import com.sp.utility.SearchResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginResponse {
	private String responseCode;
	private String responseMessage;
	private String userEmail;

}
