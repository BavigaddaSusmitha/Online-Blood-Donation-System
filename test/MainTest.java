package com.blooddonation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.blooddonation.entities.BloodBank;
import com.blooddonation.entities.Donor;

public class MainTest {

    static SessionFactory sessionFactory;

    @BeforeAll
    public static void setup() {

        sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();

        System.out.println(
                "Hibernate Test Started"
        );
    }

    @Test
    public void testSessionFactory() {

        assertNotNull(sessionFactory);

        System.out.println(
                "SessionFactory Created Successfully"
        );
    }

    @Test
    public void testDonorObject() {

        Donor donor = new Donor();

        donor.setName("Ramesh");

        donor.setBloodGroup("O+");

        donor.setContactNumber("9876543210");

        assertEquals(
                "Ramesh",
                donor.getName()
        );

        assertEquals(
                "O+",
                donor.getBloodGroup()
        );

        System.out.println(
                "Donor Test Passed"
        );
    }

    @Test
    public void testBloodBankObject() {

        BloodBank bank =
                new BloodBank();

        bank.setName("Red Cross");

        bank.setLocation("Hyderabad");

        assertEquals(
                "Red Cross",
                bank.getName()
        );

        assertEquals(
                "Hyderabad",
                bank.getLocation()
        );

        System.out.println(
                "Blood Bank Test Passed"
        );
    }

    @AfterAll
    public static void close() {

        if (sessionFactory != null) {

            sessionFactory.close();
        }

        System.out.println(
                "Hibernate Test Closed"
        );
    }
}