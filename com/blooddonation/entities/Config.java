package com.blooddonation.entities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import lombok.Data;
public class Config {

    private static SessionFactory sessionFactory;

    static {

        try {

            Configuration cfg =
                    new Configuration();

            // Load hibernate.cfg.xml

            cfg.configure("hibernate.cfg.xml");

            // ADD ENTITY CLASSES

            cfg.addAnnotatedClass(Donor.class);

            cfg.addAnnotatedClass(Recipient.class);

            cfg.addAnnotatedClass(BloodBank.class);

            cfg.addAnnotatedClass(BloodRequest.class);

            cfg.addAnnotatedClass(Donation.class);
            cfg.addAnnotatedClass(BloodInventory.class);

//            

            cfg.addAnnotatedClass(HealthCheck.class);

            // BUILD SESSION FACTORY

            sessionFactory =
                    cfg.buildSessionFactory();

            System.out.println(
                    "✅ Hibernate Connected Successfully");

        } catch (Throwable e) {

            System.out.println(
                    "❌ Hibernate Connection Failed");

            e.printStackTrace();

            sessionFactory = null;
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    private Config() {

    }
}