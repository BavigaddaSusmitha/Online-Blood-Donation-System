package com.blooddonation.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.BloodBank;
import com.blooddonation.entities.Donation;
import com.blooddonation.entities.Donor;
import com.blooddonation.service.DonationService;

public class DonationServiceImpl implements DonationService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveDonation(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donation donation = new Donation();

        System.out.println("Enter Donor ID:");
        int donorId = sc.nextInt();

        Donor donor = session.find(Donor.class, donorId);

        if (donor == null) {

            System.out.println("Donor ID Not Found");

            session.close();

            return;
        }

        System.out.println("Enter Blood Bank ID:");
        int bloodBankId = sc.nextInt();

        BloodBank bloodBank = session.find(BloodBank.class, bloodBankId);

        if (bloodBank == null) {

            System.out.println("Blood Bank ID Not Found");

            session.close();

            return;
        }

        System.out.println("Enter Quantity Donated (in ml):");
        int quantity = sc.nextInt();

        donation.setDonor(donor);
        donation.setBloodBank(bloodBank);
        donation.setDonationDate(LocalDate.now());
        donation.setQuantity(quantity);

        // Update donor's last donation date
        donor.setLastDonationDate(LocalDate.now());
        session.merge(donor);

        session.persist(donation);

        transaction.commit();

        session.close();

        System.out.println("Donation Saved Successfully");
    }

    @Override
    public Donation getDonation(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        Donation donation = session.find(Donation.class, id);

        session.close();

        return donation;
    }

    @Override
    public List<Donation> getAllDonations(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<Donation> query =
                session.createQuery("from Donation", Donation.class);

        List<Donation> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateDonation(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donation donation = session.find(Donation.class, id);

        if (donation == null) {

            System.out.println("Donation ID Not Found");

            session.close();

            return 0;
        }

        System.out.println("Enter New Quantity (in ml):");
        int quantity = sc.nextInt();

        donation.setQuantity(quantity);

        session.merge(donation);

        transaction.commit();

        session.close();

        System.out.println("Donation Updated Successfully");

        return 1;
    }

    @Override
    public int deleteDonation(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Donation donation = session.find(Donation.class, id);

        if (donation == null) {

            System.out.println("Donation ID Not Found");

            session.close();

            return 0;
        }

        session.remove(donation);

        transaction.commit();

        session.close();

        System.out.println("Donation Deleted Successfully");

        return 1;
    }
}