package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.Donation;

public interface DonationService {

    void saveDonation(SessionFactory sessionFactory);

    Donation getDonation(SessionFactory sessionFactory, int id);

    List<Donation> getAllDonations(SessionFactory sessionFactory);

    int updateDonation(SessionFactory sessionFactory, int id);

    int deleteDonation(SessionFactory sessionFactory, int id);
}