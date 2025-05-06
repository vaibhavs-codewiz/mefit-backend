package com.sp.utility;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response implements Serializable{
	public String responseCode;
	public String responseMessage;

}
