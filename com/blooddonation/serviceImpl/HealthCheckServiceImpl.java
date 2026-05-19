package com.blooddonation.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.Donor;
import com.blooddonation.entities.HealthCheck;
import com.blooddonation.service.HealthCheckService;

public class HealthCheckServiceImpl implements HealthCheckService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveHealthCheck(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        HealthCheck healthCheck = new HealthCheck();

        System.out.println("Enter Donor ID:");
        int donorId = sc.nextInt();

        Donor donor = session.find(Donor.class, donorId);

        if (donor == null) {

            System.out.println("Donor ID Not Found");

            session.close();

            return;
        }

        System.out.println("Enter Weight (in kg):");
        double weight = sc.nextDouble();

        sc.nextLine();

        System.out.println("Enter Blood Pressure (e.g. 120/80):");
        String bloodPressure = sc.nextLine();

        System.out.println("Enter Hemoglobin Level (in g/dL):");
        double hemoglobinLevel = sc.nextDouble();

        sc.nextLine();

        System.out.println("Is Alcoholic? (Yes/No):");
        String isAlcoholic = sc.nextLine();

        System.out.println("Is Smoker? (Yes/No):");
        String isSmoker = sc.nextLine();

        System.out.println("Has Any Disease? (Yes/No):");
        String hasDisease = sc.nextLine();

        String diseaseDetails = "";

        if (hasDisease.equalsIgnoreCase("Yes")) {

            System.out.println("Enter Disease Details:");
            diseaseDetails = sc.nextLine();
        }

        // Eligibility Logic
        String isEligible = "Yes";

        if (isAlcoholic.equalsIgnoreCase("Yes")
                || isSmoker.equalsIgnoreCase("Yes")
                || hasDisease.equalsIgnoreCase("Yes")
                || hemoglobinLevel < 12.5
                || weight < 50) {

            isEligible = "No";
        }

        healthCheck.setDonor(donor);
        healthCheck.setCheckDate(LocalDate.now());
        healthCheck.setWeight(weight);
        healthCheck.setBloodPressure(bloodPressure);
        healthCheck.setHemoglobinLevel(hemoglobinLevel);
        healthCheck.setIsAlcoholic(isAlcoholic);
        healthCheck.setIsSmoker(isSmoker);
        healthCheck.setHasDisease(hasDisease);
        healthCheck.setDiseaseDetails(diseaseDetails);
        healthCheck.setIsEligible(isEligible);

        session.persist(healthCheck);

        transaction.commit();

        session.close();

        System.out.println("Health Check Saved Successfully");
        System.out.println("Donor Eligibility Status: " + isEligible);
    }

    @Override
    public HealthCheck getHealthCheck(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        HealthCheck healthCheck = session.find(HealthCheck.class, id);

        session.close();

        return healthCheck;
    }

    @Override
    public List<HealthCheck> getAllHealthChecks(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<HealthCheck> query =
                session.createQuery("from HealthCheck", HealthCheck.class);

        List<HealthCheck> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateHealthCheck(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        HealthCheck healthCheck = session.find(HealthCheck.class, id);

        if (healthCheck == null) {

            System.out.println("Health Check ID Not Found");

            session.close();

            return 0;
        }

        sc.nextLine();

        System.out.println("Enter New Eligibility Status (Yes/No):");
        String isEligible = sc.nextLine();

        healthCheck.setIsEligible(isEligible);

        session.merge(healthCheck);

        transaction.commit();

        session.close();

        System.out.println("Health Check Updated Successfully");

        return 1;
    }

    @Override
    public int deleteHealthCheck(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        HealthCheck healthCheck = session.find(HealthCheck.class, id);

        if (healthCheck == null) {

            System.out.println("Health Check ID Not Found");

            session.close();

            return 0;
        }

        session.remove(healthCheck);

        transaction.commit();

        session.close();

        System.out.println("Health Check Deleted Successfully");

        return 1;
    }
}