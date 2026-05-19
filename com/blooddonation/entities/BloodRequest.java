package com.blooddonation.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    private String bloodGroup;

    private LocalDate requestDate;

    // Status values: PENDING, APPROVED, REJECTED
    private String status;
}