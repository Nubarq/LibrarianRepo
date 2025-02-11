package az.developia.librarian.service;

import az.developia.librarian.dto.request.SigninRequest;
import az.developia.librarian.dto.request.UserRequest;
import az.developia.librarian.dto.response.JwtAuthenticationResponse;
import az.developia.librarian.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService {


    public UserDetailsService userDetailsService();

    JwtAuthenticationResponse signupForLibrarian(UserRequest signUpRequest) throws MessagingException;

    JwtAuthenticationResponse signupForStudent(UserRequest signUpRequest) throws MessagingException;

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    void deleteStudent(Integer studentId);
    User editStudent(Integer studentId, UserRequest updatedStudent);

    List<User> searchStudents(String name, String email);
}
