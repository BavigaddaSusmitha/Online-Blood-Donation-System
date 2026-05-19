//package com.blooddonation.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class Recipient {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//
//    private String bloodGroup;
//
//    private String contactNumber;
//
//    private String address;
//}
package com.blooddonation.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String bloodGroup;

    private String contactNumber;

    private String address;
}