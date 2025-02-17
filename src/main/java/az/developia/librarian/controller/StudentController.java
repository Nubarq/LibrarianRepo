package az.developia.librarian.controller;

import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.entity.Student;
import az.developia.librarian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @DeleteMapping("/deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_STUDENT')")
    public void deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/editStudent/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_EDIT_STUDENT')")
    public Student editStudent(@PathVariable Integer studentId, @RequestBody StudentRequest updatedStudent){
        return studentService.editStudent(studentId,updatedStudent);
    }

    @GetMapping("/searchStudents")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_STUDENTS')")
    public List<Student> searchStudents(@RequestParam(required = false) String name
            , @RequestParam(required = false) String email){
        return studentService.searchStudents(name,email);
    }
}
