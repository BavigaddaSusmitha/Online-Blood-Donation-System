//package com.blooddonation.entities;
//
//import java.time.LocalDate;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class HealthCheck {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "donor_id")
//    private Donor donor;
//
//    private LocalDate checkDate;
//
//    // Weight in kg
//    private Double weight;
//
//    // Format: "120/80"
//    private String bloodPressure;
//
//    // Hemoglobin level in g/dL
//    private Double hemoglobinLevel;
//
//    // Yes / No
//    private String isAlcoholic;
//
//    // Yes / No
//    private String isSmoker;
//
//    // Yes / No
//    private String hasDisease;
//
//    private String diseaseDetails;
//
//    // Yes / No - final eligibility result
//    private String isEligible;
//}

package com.blooddonation.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HealthCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    private LocalDate checkDate;

    // Weight in kg
    private Double weight;

    // Format: "120/80"
    private String bloodPressure;

    // Hemoglobin level in g/dL
    private Double hemoglobinLevel;

    // Yes / No
    private String isAlcoholic;

    // Yes / No
    private String isSmoker;

    // Yes / No
    private String hasDisease;

    private String diseaseDetails;

    // Yes / No - final eligibility result
    private String isEligible;
}