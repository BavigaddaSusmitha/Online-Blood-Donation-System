//package com.blooddonation.entities;
// 
//import jakarta.persistence.*;
//import lombok.Data;
// 
//@Entity
//@Data
//public class BloodBank {
// 
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
// 
//    private String name;
// 
//    private String location;
// 
//    private String contactNumber;
//}
// 
package com.blooddonation.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    private String contactNumber;
}