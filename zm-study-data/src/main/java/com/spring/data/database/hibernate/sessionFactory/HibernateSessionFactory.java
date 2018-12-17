/*
 * Copyright (c) 2018 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.data.database.hibernate.sessionFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

/**
 * HibernateSessionFactory.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年11月29日$
 * @since 1.0
 */
@Component
public class HibernateSessionFactory
{

    /**
     * open session
     * @return
     */
    public static Session openSession()
    {
        // "./hbm/hibernateConfig.xml"
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("./hibernate/hibernateConfig.xml").build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session openSession = sessionFactory.openSession();
//        Session openSession = sessionFactory.getCurrentSession();
        return openSession;
    }

    /**
     * close session
     */
    public static void closeSession(Session session)
    {
        session.disconnect();
        session.close();
    }

}
