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
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Attendance.class)
                .addAnnotatedClass(AttendanceId.class)
                .addAnnotatedClass(Lecture.class)
                .addAnnotatedClass(Student.class)
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
        s.setEmail("admin@test.com");
        s.setUsername("testing");
        s.setPassword("testpassowrd");
        session.save(s);

        Student student = new Student();
        student.setfName("student1");
        student.setUsername("student");
        session.save(s);

        Lecture lecture = new Lecture();
        lecture.setId("123456");
        session.save(lecture);

        Attendance attendance = new Attendance();
        attendance.setId(new AttendanceId(lecture.getId(), student.getId()));
        attendance.setLecture(lecture);
        attendance.setStudent(student);
        attendance.setAttended(true);

        student.getAttendances().add(attendance);
        lecture.getAttendances().add(attendance);

        session.save(attendance);
        session.getTransaction().commit();
    }


    @After
    public void destroy() {
        sf.close();
    }
}
