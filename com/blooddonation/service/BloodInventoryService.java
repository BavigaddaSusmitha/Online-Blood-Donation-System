package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.BloodInventory;

public interface BloodInventoryService {

    void saveBloodInventory(SessionFactory sessionFactory);

    BloodInventory getBloodInventory(SessionFactory sessionFactory, int id);

    List<BloodInventory> getAllBloodInventories(SessionFactory sessionFactory);

    int updateBloodInventory(SessionFactory sessionFactory, int id);

    int deleteBloodInventory(SessionFactory sessionFactory, int id);
}