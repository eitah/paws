package com.sprinkles.models;

import com.sprinkles.util.Mysql;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/10/16.
 */
public class ShelterTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table shelters").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Before each finished");

    }
    @Test
    public void shouldCreateNewShelterAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s = new Shelter("Sprinkles Fun Factory of People");
        session.save(s);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, s.getId());
    }

}