package com.blooddonation.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.Donor;
import com.blooddonation.service.DonorService;

public class DonorServiceImpl implements DonorService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveDonor(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donor donor = new Donor();

        sc.nextLine();

        System.out.println("Enter Name:");
        String name = sc.nextLine();

        System.out.println("Enter Age:");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.println("Enter Gender (Male/Female/Other):");
        String gender = sc.nextLine();

        System.out.println("Enter Blood Group (e.g. A+, B-, O+,O-):");
        String bloodGroup = sc.nextLine();

        System.out.println("Enter Contact Number:");
        String contactNumber = sc.nextLine();

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        donor.setName(name);
        donor.setAge(age);
        donor.setGender(gender);
        donor.setBloodGroup(bloodGroup);
        donor.setContactNumber(contactNumber);
        donor.setAddress(address);
        donor.setEmail(email);
        donor.setLastDonationDate(null);

        session.persist(donor);

        transaction.commit();

        session.close();

        System.out.println("Donor Saved Successfully");
    }

    @Override
    public Donor getDonor(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        Donor donor = session.find(Donor.class, id);

        session.close();

        return donor;
    }

    @Override
    public List<Donor> getAllDonors(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<Donor> query =
                session.createQuery("from Donor", Donor.class);

        List<Donor> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateDonor(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donor donor = session.find(Donor.class, id);

        if (donor == null) {

            System.out.println("Donor ID Not Found");

            session.close();

            return 0;
        }

        sc.nextLine();

        System.out.println("Enter New Contact Number:");
        String contactNumber = sc.nextLine();

        System.out.println("Enter New Address:");
        String address = sc.nextLine();

        System.out.println("Enter New Email:");
        String email = sc.nextLine();

        System.out.println("Enter Last Donation Date (YYYY-MM-DD) or press Enter to skip:");
        String dateInput = sc.nextLine();

        donor.setContactNumber(contactNumber);
        donor.setAddress(address);
        donor.setEmail(email);

        if (!dateInput.isEmpty()) {
            donor.setLastDonationDate(LocalDate.parse(dateInput));
        }

        session.merge(donor);

        transaction.commit();

        session.close();

        System.out.println("Donor Updated Successfully");

        return 1;
    }

    @Override
    public int deleteDonor(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donor donor = session.find(Donor.class, id);

        if (donor == null) {

            System.out.println("Donor ID Not Found");

            session.close();

            return 0;
        }

        session.remove(donor);

        transaction.commit();

        session.close();

        System.out.println("Donor Deleted Successfully");

        return 1;
    }
}