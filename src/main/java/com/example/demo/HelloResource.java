package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Person;

import java.util.List;

@Path("/pat")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }





    @POST
    @Path("/{vorname}/{nachname}")
    public void load(@PathParam("vorname") String vorname, @PathParam("nachname") String nachname){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        Person p = new Person(vorname,nachname);

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();



    }

    @GET
    @Path("/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> suche(@PathParam("search") String search){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        Query qu = em.createQuery("select p from Person p where p.vorname like :value1 OR p.nachname like :value1");
        qu.setParameter("value1","%"+ search+"%");


        List<Person> p = qu.getResultList();

        return p;
    }

    @DELETE
    @Path("/{search}")
    public void loeschn(@PathParam("search") int search){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        Query qu = em.createQuery("delete from Person p where p.id = :value1");
        qu.setParameter("value1", search);
        em.getTransaction().begin();
         qu.executeUpdate();
        em.getTransaction().commit();


        //List<Person> p = qu.getResultList();


    }



}