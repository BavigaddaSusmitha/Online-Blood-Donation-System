package com.blooddonation.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blooddonation.entities.BloodBank;
import com.blooddonation.entities.BloodInventory;
import com.blooddonation.service.BloodInventoryService;

public class BloodInventoryServiceImpl implements BloodInventoryService {

    Session session;

    Transaction transaction;

    Scanner sc = new Scanner(System.in);

    @Override
    public void saveBloodInventory(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodInventory inventory = new BloodInventory();

        System.out.println("Enter Blood Bank ID:");
        int bloodBankId = sc.nextInt();

        BloodBank bloodBank = session.find(BloodBank.class, bloodBankId);

        if (bloodBank == null) {

            System.out.println("Blood Bank ID Not Found");

            session.close();

            return;
        }

        sc.nextLine();

        System.out.println("Enter Blood Group (e.g. A+, B-, O+):");
        String bloodGroup = sc.nextLine();

        System.out.println("Enter Available Quantity (in ml):");
        int quantity = sc.nextInt();

        inventory.setBloodBank(bloodBank);
        inventory.setBloodGroup(bloodGroup);
        inventory.setQuantity(quantity);

        session.persist(inventory);

        transaction.commit();

        session.close();

        System.out.println("Blood Inventory Saved Successfully");
    }

    @Override
    public BloodInventory getBloodInventory(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        BloodInventory inventory = session.find(BloodInventory.class, id);

        session.close();

        return inventory;
    }

    @Override
    public List<BloodInventory> getAllBloodInventories(SessionFactory sessionFactory) {

        session = sessionFactory.openSession();

        Query<BloodInventory> query =
                session.createQuery("from BloodInventory", BloodInventory.class);

        List<BloodInventory> list = query.getResultList();

        session.close();

        return list;
    }

    @Override
    public int updateBloodInventory(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodInventory inventory = session.find(BloodInventory.class, id);

        if (inventory == null) {

            System.out.println("Blood Inventory ID Not Found");

            session.close();

            return 0;
        }

        System.out.println("Enter New Quantity (in ml):");
        int quantity = sc.nextInt();

        inventory.setQuantity(quantity);

        session.merge(inventory);

        transaction.commit();

        session.close();

        System.out.println("Blood Inventory Updated Successfully");

        return 1;
    }

    @Override
    public int deleteBloodInventory(SessionFactory sessionFactory, int id) {

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();

        BloodInventory inventory = session.find(BloodInventory.class, id);

        if (inventory == null) {

            System.out.println("Blood Inventory ID Not Found");

            session.close();

            return 0;
        }

        session.remove(inventory);

        transaction.commit();

        session.close();

        System.out.println("Blood Inventory Deleted Successfully");

        return 1;
    }
}