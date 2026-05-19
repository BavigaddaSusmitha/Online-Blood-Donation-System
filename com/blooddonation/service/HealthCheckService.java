package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.HealthCheck;

public interface HealthCheckService {

    void saveHealthCheck(SessionFactory sessionFactory);

    HealthCheck getHealthCheck(SessionFactory sessionFactory, int id);

    List<HealthCheck> getAllHealthChecks(SessionFactory sessionFactory);

    int updateHealthCheck(SessionFactory sessionFactory, int id);

    int deleteHealthCheck(SessionFactory sessionFactory, int id);
}