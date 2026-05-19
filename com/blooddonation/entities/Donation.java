//package com.blooddonation.entities;
//
//import java.time.LocalDate;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class Donation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "donor_id")
//    private Donor donor;
//
//    @ManyToOne
//    @JoinColumn(name = "blood_bank_id")
//    private BloodBank bloodBank;
//
//    private LocalDate donationDate;
//
//    // Quantity in ml (e.g., 450)
//    private Integer quantity;
//} 
package com.blooddonation.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;

    private LocalDate donationDate;

    // Quantity in ml
    private Integer quantity;
}