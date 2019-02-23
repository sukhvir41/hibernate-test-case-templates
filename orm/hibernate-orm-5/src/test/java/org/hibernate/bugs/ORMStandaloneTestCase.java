package org.hibernate.bugs;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * This template demonstrates how to develop a standalone test case for Hibernate ORM.  Although this is perfectly
 * acceptable as a reproducer, usage of ORMUnitTestCase is preferred!
 */
public class ORMStandaloneTestCase {

    private SessionFactory sf;

    @Before
    public void setup() {
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
                // Add in any settings that are specific to your test. See resources/hibernate.properties for the defaults.
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.hbm2ddl.auto", "update");

        Metadata metadata = new MetadataSources(srb.build())
                // Add your entities here.
                //	.addAnnotatedClass( Foo.class )
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Attendance.class)
                .addAnnotatedClass(AttendanceId.class)
                .addAnnotatedClass(Lecture.class)
                .addAnnotatedClass(Student.class)
                //.addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(User.class)
                .buildMetadata();

        sf = metadata.buildSessionFactory();
    }

    // Add your tests, using standard JUnit.

    @Test
    public void hhh123Test() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        Admin s = new Admin();
        s.setEmail("sdfsfd@df.occom");
        s.setUsername("dfsdfsdff");
        session.save(s);
        session.getTransaction().commit();
    }


    @After
    public void destroy() {
        sf.close();
    }
}
