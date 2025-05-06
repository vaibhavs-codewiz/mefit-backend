package com.sp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int paymentId;
	
	@ManyToOne
    @JoinColumn(name = "gymId")
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    private double paymentAmount;
    private LocalDate PaymentDate;
	
    @OneToOne
    @JoinColumn(name="bookingId")
    private UsersSlot userSlot;
}
