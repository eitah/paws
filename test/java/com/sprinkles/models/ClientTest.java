package com.sprinkles.models;

import com.sprinkles.util.Mysql;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("Mr Pickles");
        session.save(c);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, c.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDueToNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("Mr Pickles0123012323123123123123123112313223132123131313131131331131321165614561441564156313");
        try {
            session.save(c);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}