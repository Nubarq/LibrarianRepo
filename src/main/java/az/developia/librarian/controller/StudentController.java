package az.developia.librarian.controller;

import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.entity.Student;
import az.developia.librarian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @DeleteMapping("/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/editStudent/{studentId}")
    public Student editStudent(@PathVariable Integer studentId, @RequestBody StudentRequest updatedStudent){
        return studentService.editStudent(studentId,updatedStudent);
    }

    @GetMapping("/searchStudents")
    public List<Student> searchStudents(@RequestParam(required = false) String name
            , @RequestParam(required = false) String email){
        return studentService.searchStudents(name,email);
    }
}
