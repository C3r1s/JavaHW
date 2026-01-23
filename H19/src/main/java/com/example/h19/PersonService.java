package com.example.h19;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class PersonService {
    @Inject
    private EntityManager em;

    public List<Person> getAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public Person getById(Long id) {
        return em.find(Person.class, id);
    }

    public Person create(Person person) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        return person;
    }

    public Person update(Person person) {
        em.getTransaction().begin();
        Person updatedPerson = em.merge(person);
        em.getTransaction().commit();
        return updatedPerson;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Person person = getById(id);
        if (person != null) {
            em.remove(person);
        }
        em.getTransaction().commit();
    }
}