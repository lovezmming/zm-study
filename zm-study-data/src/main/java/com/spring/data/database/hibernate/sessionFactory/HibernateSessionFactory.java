package com.spring.data.database.hibernate.sessionFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactory
{

    public static Session openSession()
    {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("./hibernate/hibernateConfig.xml").build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session openSession = sessionFactory.openSession();
//        Session openSession = sessionFactory.getCurrentSession();
        return openSession;
    }

    public static void closeSession(Session session)
    {
        session.disconnect();
        session.close();
    }

}
