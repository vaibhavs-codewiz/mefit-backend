package com.sp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int gymId;
    @Column(unique = true)
    private String name;
    private String address;
    private String ownerName;
    private String contactNo;
    @Column(unique = true)
    private String email;
    private double monthlyFee;
    private double dailyFee;
    private String password;
    

}
