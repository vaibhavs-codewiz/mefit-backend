package com.sp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int trainerId;
    private String name;
    private String contactNo;
    private String email;
    private String acheivements;

    @ManyToOne
    @JoinColumn(name = "gymId") // foreign key column in trainer table
    private Gym gym;
}
