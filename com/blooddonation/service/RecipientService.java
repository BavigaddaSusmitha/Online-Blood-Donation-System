package com.blooddonation.service;

import java.util.List;
import org.hibernate.SessionFactory;
import com.blooddonation.entities.Recipient;

public interface RecipientService {

    void saveRecipient(SessionFactory sessionFactory);

    Recipient getRecipient(SessionFactory sessionFactory, int id);

    List<Recipient> getAllRecipients(SessionFactory sessionFactory);

    int updateRecipient(SessionFactory sessionFactory, int id);

    int deleteRecipient(SessionFactory sessionFactory, int id);
}