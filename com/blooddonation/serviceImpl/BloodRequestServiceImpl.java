package com.blooddonation.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.BloodRequest;
import com.blooddonation.entities.Recipient;
import com.blooddonation.service.BloodRequestService;

public class BloodRequestServiceImpl implements BloodRequestService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveBloodRequest(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodRequest bloodRequest = new BloodRequest();

        System.out.println("Enter Recipient ID:");
        int recipientId = sc.nextInt();

        Recipient recipient = session.find(Recipient.class, recipientId);

        if (recipient == null) {

            System.out.println("Recipient ID Not Found");

            session.close();

            return;
        }

        sc.nextLine();

        System.out.println("Enter Blood Group Required (e.g. A+, B-, O+):");
        String bloodGroup = sc.nextLine();

        bloodRequest.setRecipient(recipient);
        bloodRequest.setBloodGroup(bloodGroup);
        bloodRequest.setRequestDate(LocalDate.now());
        bloodRequest.setStatus("PENDING");

        session.persist(bloodRequest);

        transaction.commit();

        session.close();

        System.out.println("Blood Request Saved Successfully");
    }

    @Override
    public BloodRequest getBloodRequest(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        BloodRequest bloodRequest = session.find(BloodRequest.class, id);

        session.close();

        return bloodRequest;
    }

    @Override
    public List<BloodRequest> getAllBloodRequests(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<BloodRequest> query =
                session.createQuery("from BloodRequest", BloodRequest.class);

        List<BloodRequest> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateBloodRequest(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodRequest bloodRequest = session.find(BloodRequest.class, id);

        if (bloodRequest == null) {

            System.out.println("Blood Request ID Not Found");

            session.close();

            return 0;
        }

        sc.nextLine();

        System.out.println("Enter New Status (PENDING / APPROVED / REJECTED):");
        String status = sc.nextLine();

        bloodRequest.setStatus(status);

        session.merge(bloodRequest);

        transaction.commit();

        session.close();

        System.out.println("Blood Request Updated Successfully");

        return 1;
    }

    @Override
    public int deleteBloodRequest(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodRequest bloodRequest = session.find(BloodRequest.class, id);

        if (bloodRequest == null) {

            System.out.println("Blood Request ID Not Found");

            session.close();

            return 0;
        }

        session.remove(bloodRequest);

        transaction.commit();

        session.close();

        System.out.println("Blood Request Deleted Successfully");

        return 1;
    }
}