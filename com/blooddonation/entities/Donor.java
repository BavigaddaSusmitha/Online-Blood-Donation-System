//package com.blooddonation.entities;
//
//import java.time.LocalDate;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class Donor {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//
//    private Integer age;
//
//    private String gender;
//
//    private String bloodGroup;
//
//    private String contactNumber;
//
//    private String address;
//
//    private LocalDate lastDonationDate;
//
//    private String email;
//}
package com.blooddonation.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    private String bloodGroup;

    private String contactNumber;

    private String address;

    private LocalDate lastDonationDate;

    private String email;
}