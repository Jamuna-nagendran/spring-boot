package com.example.springfox.repository;
import com.example.springfox.model.Student;
import java.util.List;

interface StudentCustomRepository {
    List<Student> findByAgeGreaterThan(Integer age);
    List<Student> findByNameContaining(String name);
    void deleteByName(String name);
    long countByAgeGreaterThan(Integer age);
}
