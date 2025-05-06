package com.sp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String address;
	private String contactNo;
    private String password;
	private String gender;
	private int height;
	private int weight;
	
	
}
