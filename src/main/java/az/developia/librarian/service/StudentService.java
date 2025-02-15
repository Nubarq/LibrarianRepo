package az.developia.librarian.service;

import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.entity.Student;

import java.util.List;

public interface StudentService {
    void deleteStudent(Integer studentId);

    Student editStudent(Integer studentId, StudentRequest updatedStudent);
    List<Student> searchStudents(String name, String email);
}
