package com.example.studentapi.repository;

import com.example.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring Data JPA genera automáticamente la implementación
    // de estos métodos basándose en el nombre del método

    Optional<Student> findByEmail(String email);
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    boolean existsByEmail(String email);
}
