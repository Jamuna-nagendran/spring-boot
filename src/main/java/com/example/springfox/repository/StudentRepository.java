package com.example.springfox.repository;
import com.example.springfox.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, StudentCustomRepository {

}


