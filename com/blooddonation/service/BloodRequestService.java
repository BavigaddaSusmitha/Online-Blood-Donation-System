package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.BloodRequest;

public interface BloodRequestService {

    void saveBloodRequest(SessionFactory sessionFactory);

    BloodRequest getBloodRequest(SessionFactory sessionFactory, int id);

    List<BloodRequest> getAllBloodRequests(SessionFactory sessionFactory);

    int updateBloodRequest(SessionFactory sessionFactory, int id);

    int deleteBloodRequest(SessionFactory sessionFactory, int id);
}