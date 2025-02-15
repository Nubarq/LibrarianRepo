package az.developia.librarian.service.impl;

import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.entity.Student;
import az.developia.librarian.entity.User;
import az.developia.librarian.exeption.CustomException;
import az.developia.librarian.repository.StudentRepository;
import az.developia.librarian.repository.UserRepository;
import az.developia.librarian.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;



    // Delete a student by ID
    public void deleteStudent(Integer studentId) {
        // Check if the user exists and is a student
        Optional<Student> userOptional = studentRepository.findById(studentId);
        if (userOptional.isEmpty() ) {
            throw new CustomException("Student not found with ID: " + studentId);
        }

        // Delete the student
        studentRepository.deleteById(studentId);
    }


    // Edit a student
    public Student editStudent(Integer studentId, StudentRequest updatedStudent) {
        // Check if the user exists and is a student
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Student existingStudent =studentOptional.get();
        Optional<User> userOptional = userRepository.findById(existingStudent.getUser().getId());

        if (userOptional.isEmpty()) {
            throw new CustomException("Student not found with ID: " + studentId);
        }

        // Get the existing student
        User existinguser = userOptional.get();

        // Update the fields (only non-null fields)
        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
            existinguser.setName((updatedStudent.getName()));
        }
        if (updatedStudent.getEmail() != null) {
            existingStudent.setEmail(updatedStudent.getEmail());
            existinguser.setEmail(updatedStudent.getEmail());
        }
        if (updatedStudent.getPassword() != null) {
            existinguser.setPassword(updatedStudent.getPassword()); // Ensure password is hashed
        }
        existingStudent.setUpdatedAt(LocalDateTime.now());
        existinguser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existinguser);
        // Save the updated student
        return studentRepository.save(existingStudent);
    }


    // Search for students by name or email
    public List<Student> searchStudents(String name, String email) {
        if (name != null && !name.isEmpty()) {
            return studentRepository.findByNameContainingIgnoreCase(name);
        } else if (email != null && !email.isEmpty()) {
            return studentRepository.findByEmailContainingIgnoreCase(email);
        } else {
            // If no search criteria are provided, return all students
            return studentRepository.findAll();
        }
    }
}
