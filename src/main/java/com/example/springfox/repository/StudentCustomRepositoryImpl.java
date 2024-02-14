package com.example.springfox.repository;
import com.example.springfox.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findByAgeGreaterThan(Integer age) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.age > :age");
        query.setParameter("age", age);
        return query.getResultList();
    }

    @Override
    public List<Student> findByNameContaining(String name) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public void deleteByName(String name) {
        Query query = entityManager.createQuery("DELETE FROM Student s WHERE s.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public long countByAgeGreaterThan(Integer age) {
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM Student s WHERE s.age > :age");
        query.setParameter("age", age);
        return (long) query.getSingleResult();
    }
}
