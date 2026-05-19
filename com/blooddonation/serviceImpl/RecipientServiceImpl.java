package com.blooddonation.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.Recipient;
import com.blooddonation.service.RecipientService;

public class RecipientServiceImpl implements RecipientService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveRecipient(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Recipient recipient = new Recipient();

        sc.nextLine();

        System.out.println("Enter Name:");
        String name = sc.nextLine();

        System.out.println("Enter Blood Group (e.g. A+, B-, O+):");
        String bloodGroup = sc.nextLine();

        System.out.println("Enter Contact Number:");
        String contactNumber = sc.nextLine();

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        recipient.setName(name);
        recipient.setBloodGroup(bloodGroup);
        recipient.setContactNumber(contactNumber);
        recipient.setAddress(address);

        session.persist(recipient);

        transaction.commit();

        session.close();

        System.out.println("Recipient Saved Successfully");
    }

    @Override
    public Recipient getRecipient(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        Recipient recipient = session.find(Recipient.class, id);

        session.close();

        return recipient;
    }

    @Override
    public List<Recipient> getAllRecipients(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<Recipient> query =
                session.createQuery("from Recipient", Recipient.class);

        List<Recipient> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateRecipient(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Recipient recipient = session.find(Recipient.class, id);

        if (recipient == null) {

            System.out.println("Recipient ID Not Found");

            session.close();

            return 0;
        }

        sc.nextLine();

        System.out.println("Enter New Contact Number:");
        String contactNumber = sc.nextLine();

        System.out.println("Enter New Address:");
        String address = sc.nextLine();

        recipient.setContactNumber(contactNumber);
        recipient.setAddress(address);

        session.merge(recipient);

        transaction.commit();

        session.close();

        System.out.println("Recipient Updated Successfully");

        return 1;
    }

    @Override
    public int deleteRecipient(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        Recipient recipient = session.find(Recipient.class, id);

        if (recipient == null) {

            System.out.println("Recipient ID Not Found");

            session.close();

            return 0;
        }

        session.remove(recipient);

        transaction.commit();

        session.close();

        System.out.println("Recipient Deleted Successfully");

        return 1;
    }
}