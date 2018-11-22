package com.spring.data.database.hibernate.dao;

import com.spring.data.database.hibernate.HibernateDaoService;
import com.spring.data.database.hibernate.sessionFactory.HibernateSessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "HibernateDaoServiceImpl")
public class HibernateDaoServiceImpl implements HibernateDaoService
{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Object findById(Class<?> clazz, String id)
    {
        return entityManager.find(clazz, id);
    }

    @Transactional
    @Override
    public Object findBySession(Class<?> clazz, String id)
    {
        return HibernateSessionFactory.openSession().find(clazz, id);
    }

}
