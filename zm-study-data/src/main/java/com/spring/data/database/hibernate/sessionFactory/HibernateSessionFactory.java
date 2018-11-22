package com.spring.data.database.hibernate.sessionFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory
{

    public static Session openSession(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("./hibernate/hibernateConfig.xml").build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session openSession = sessionFactory.openSession();
        return openSession;
    }

}
