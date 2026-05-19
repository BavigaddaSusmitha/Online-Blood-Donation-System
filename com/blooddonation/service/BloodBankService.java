package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.BloodBank;

public interface BloodBankService {

    void saveBloodBank(SessionFactory sessionFactory);

    BloodBank getBloodBank(SessionFactory sessionFactory, int id);

    List<BloodBank> getAllBloodBanks(SessionFactory sessionFactory);

    int updateBloodBank(SessionFactory sessionFactory, int id);

    int deleteBloodBank(SessionFactory sessionFactory, int id);
}