package com.blooddonation.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BloodInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;

    // Blood group values: A+, A-, B+, B-, O+, O-, AB+, AB-
    private String bloodGroup;

    // Available quantity in ml
    private Integer quantity;
}
