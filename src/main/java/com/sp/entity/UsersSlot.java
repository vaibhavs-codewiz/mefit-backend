package com.sp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class UsersSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookingId;

    private long bookingDays; // You can use LocalDate or another type if preferred
    private String subscriptionType;
    private String dates;
    @ManyToOne
    @JoinColumn(name = "gymId")
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
}
