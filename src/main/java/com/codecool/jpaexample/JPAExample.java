package com.codecool.jpaexample;

import com.codecool.jpaexample.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JPAExample {

    public static void populateDb(EntityManager em) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate1 = Calendar.getInstance().getTime();
        Date birthDate2 = Calendar.getInstance().getTime();
        try {
            birthDate1 = sdf.parse("1997-07-21");
            birthDate2 = sdf.parse("1993-12-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Klass classBp2 = new Klass("Budapest 2016-2", CCLocation.BUDAPEST);
        Address address = new Address("Hungary", "1234", "Budapest", "Macskakő út 5.");
        List<String> phoneNumbers1 = Arrays.asList("123-123-123", "777-777-777");
        Student student = new Student("Ödön", "odon@tokodon.hu", birthDate1, address, phoneNumbers1, classBp2);
        classBp2.addStudent(student);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(address);
        em.persist(classBp2);
        em.persist(student);
        transaction.commit();
        System.out.println("\n### Ödön saved.\n");

        Address address2 = new Address("Hungary", "6789", "Budapest", "Harap u. 3.");
        List<String> phoneNumbers2 = Arrays.asList("321-321-321", "666-666-666");
        Student student2 = new Student("Aladár", "ktyfl@gmail.com", birthDate2, address2, phoneNumbers2, classBp2);
        classBp2.addStudent(student2);

        transaction.begin();
        em.persist(student2);
        em.persist(classBp2);
        em.persist(address2);

//        em.remove(classBp2);

        transaction.commit();
        System.out.println("\n### Aladár saved.\n");


    }

    public static void loadClass(EntityManager em){
        em.clear();
        Klass klass = em.find(Klass.class, 1L);
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);
        em.clear(); //clear hibernate cache - force next statements to read data from db

        Student foundStudent1 = em.find(Student.class, 1L);
        System.out.println("\n--Found student #1");
        System.out.println("----name----" + foundStudent1.getName());
        System.out.println("----address of student----" + foundStudent1.getAddress());
        System.out.println();

        Student foundStudent2 = em.find(Student.class, 2L);
        System.out.println("\n--Found student #2");
        System.out.println("----name----" + foundStudent2.getName());
        System.out.println("----address of student----" + foundStudent2.getAddress());
        System.out.println();

        Address foundAddress1 = em.find(Address.class, 1L);
        System.out.println("--Found address #1");
        System.out.println("----address----" + foundAddress1.getAddr());

        Address foundAddress2 = em.find(Address.class, 2L);
        System.out.println("--Found address #2");
        System.out.println("----address----" + foundAddress2.getAddr());


        loadClass(em);

        em.close();
        emf.close();

    }
}
