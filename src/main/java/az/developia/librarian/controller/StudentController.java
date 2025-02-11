package az.developia.librarian.controller;

import az.developia.librarian.dto.request.UserRequest;
import az.developia.librarian.entity.User;
import az.developia.librarian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService userService;

    // Delete a student
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        userService.deleteStudent(studentId);
    }

    // Edit a student
    @PutMapping("/{studentId}")
    public User editStudent(@PathVariable Integer studentId, @RequestBody UserRequest updatedStudent) {
        return userService.editStudent(studentId, updatedStudent);
    }

    // Search for students
    @GetMapping("/search")
    public List<User> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return userService.searchStudents(name, email);
    }
}
