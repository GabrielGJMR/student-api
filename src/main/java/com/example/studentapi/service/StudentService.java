package com.example.studentapi.service;

import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener estudiante por ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Crear nuevo estudiante
    public Student createStudent(Student student) {

        // Validar que el email no esté duplicado
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("Email ya está en uso");
        }

        return studentRepository.save(student);
    }

    // Actualizar estudiante
    public Student updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Estudiante con id " + id + " no existe"));

        // Actualizar los campos
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }

    // Eliminar estudiante
    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException(
                    "Estudiante con id " + id + " no existe");
        }

        studentRepository.deleteById(id);
    }

    // Buscar estudiante por email
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
