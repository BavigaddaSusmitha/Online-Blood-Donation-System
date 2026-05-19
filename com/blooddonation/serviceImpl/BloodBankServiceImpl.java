package com.blooddonation.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.BloodBank;
import com.blooddonation.service.BloodBankService;

public class BloodBankServiceImpl implements BloodBankService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveBloodBank(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodBank bloodBank = new BloodBank();

        sc.nextLine();

        System.out.println("Enter Blood Bank Name:");
        String name = sc.nextLine();

        System.out.println("Enter Location:");
        String location = sc.nextLine();

        System.out.println("Enter Contact Number:");
        String contactNumber = sc.nextLine();

        bloodBank.setName(name);
        bloodBank.setLocation(location);
        bloodBank.setContactNumber(contactNumber);

        session.persist(bloodBank);

        transaction.commit();

        session.close();

        System.out.println("Blood Bank Saved Successfully");
    }

    @Override
    public BloodBank getBloodBank(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        BloodBank bloodBank = session.find(BloodBank.class, id);

        session.close();

        return bloodBank;
    }

    @Override
    public List<BloodBank> getAllBloodBanks(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<BloodBank> query =
                session.createQuery("from BloodBank", BloodBank.class);

        List<BloodBank> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateBloodBank(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodBank bloodBank = session.find(BloodBank.class, id);

        if (bloodBank == null) {

            System.out.println("Blood Bank ID Not Found");

            session.close();

            return 0;
        }

        sc.nextLine();

        System.out.println("Enter New Location:");
        String location = sc.nextLine();

        System.out.println("Enter New Contact Number:");
        String contactNumber = sc.nextLine();

        bloodBank.setLocation(location);
        bloodBank.setContactNumber(contactNumber);

        session.merge(bloodBank);

        transaction.commit();

        session.close();

        System.out.println("Blood Bank Updated Successfully");

        return 1;
    }

    @Override
    public int deleteBloodBank(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodBank bloodBank = session.find(BloodBank.class, id);

        if (bloodBank == null) {

            System.out.println("Blood Bank ID Not Found");

            session.close();

            return 0;
        }

        session.remove(bloodBank);

        transaction.commit();

        session.close();

        System.out.println("Blood Bank Deleted Successfully");

        return 1;
    }
}