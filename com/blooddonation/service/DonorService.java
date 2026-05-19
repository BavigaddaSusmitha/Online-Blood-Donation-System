package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.Donor;

public interface DonorService {

    void saveDonor(SessionFactory sessionFactory);

    Donor getDonor(SessionFactory sessionFactory, int id);

    List<Donor> getAllDonors(SessionFactory sessionFactory);

    int updateDonor(SessionFactory sessionFactory, int id);

    int deleteDonor(SessionFactory sessionFactory, int id);
}