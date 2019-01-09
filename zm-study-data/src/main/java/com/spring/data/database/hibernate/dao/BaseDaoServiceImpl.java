package com.spring.data.database.hibernate.dao;

import com.spring.common.entity.RecordSet;
import com.spring.common.util.TextUtil;
import com.spring.data.database.hibernate.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class BaseDaoServiceImpl implements BaseDaoService
{

    private Session getSession()
    {
        return HibernateSessionFactory.openSession();
    }

    private void closeSession(Session session)
    {
        HibernateSessionFactory.closeSession(session);
    }

    @Override
    public Object find(Class<?> clazz, String id)
    {
        Session session = getSession();
        Object obj = null;
        try
        {
            obj = session.find(clazz, id);
        } catch (Exception e)
        {
        } finally
        {
            closeSession(session);
        }
        return obj;
    }

    @Override
    public Object create(Object object)
    {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        Object obj = null;
        try
        {
            obj = session.save(object);
            ts.commit();
        } catch (Exception e)
        {
        } finally
        {
            closeSession(session);
        }
        return obj;
    }

    @Override
    public Object update(Object object)
    {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        try
        {
            session.update(object);
            ts.commit();
        } catch (Exception e)
        {
            ts.rollback();
        } finally
        {
            closeSession(session);
        }
        return object;
    }

    @Override
    public void delete(Object object)
    {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        try
        {
            session.delete(object);
            ts.commit();
        } catch (Exception e)
        {
            ts.rollback();
        } finally
        {
            closeSession(session);
        }
    }

    @Override
    public void delete(Class<?> clazz, String id)
    {
        Session session = getSession();
        Transaction ts = session.beginTransaction();
        try
        {
            session.delete(find(clazz, id));
            ts.commit();
        } catch (Exception e)
        {
            ts.rollback();
        } finally
        {
            closeSession(session);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public RecordSet query(Class<?> clazz, String name, Map<String, Object> params, Integer start, Integer max)
    {
        Session session = getSession();
        List<Object> results = null;
        try
        {
            StringBuffer buf = new StringBuffer();
            for (; clazz.isArray(); clazz = clazz.getComponentType())
            {
                buf.append("[]");
            }
            String className = buf.insert(0, clazz.getName()).toString();
            Query query = TextUtil.isEmpty(name) ? session.createQuery("from " + clazz.getName()) : session.getNamedQuery(className + "." + name);
            for (Map.Entry<String, Object> entry : params.entrySet())
            {
                String paramName = (String) entry.getKey();
                Object value = entry.getValue();
                if (entry.getValue() instanceof Collection)
                {
                    query.setParameterList(paramName, (Collection<?>) value);
                }
                else if (entry.getValue() instanceof Object[])
                {
                    query.setParameterList(paramName, (Object[]) value);
                }
                else
                {
                    query.setParameter(paramName, value);
                }
            }
            start = start == null ? 0 : start;
            max = max == null ? Integer.MAX_VALUE : max;
            query.setFirstResult(start).setMaxResults(max);
            results = query.list();
        } catch (Exception e)
        {
            results = new ArrayList<Object>();
        } finally
        {
            closeSession(session);
        }
        RecordSet recordSet = new RecordSet(start, max, results.size(), (Object[]) results.toArray(new Object[0]));
        return recordSet;
    }

}

