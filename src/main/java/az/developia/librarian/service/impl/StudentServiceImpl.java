package az.developia.librarian.service.impl;

import az.developia.librarian.dto.request.SigninRequest;
import az.developia.librarian.dto.request.UserRequest;
import az.developia.librarian.dto.response.JwtAuthenticationResponse;
import az.developia.librarian.entity.Librarian;
import az.developia.librarian.entity.Role;
import az.developia.librarian.entity.User;
import az.developia.librarian.exeption.CustomException;
import az.developia.librarian.mapper.LibrarianMapper;
import az.developia.librarian.mapper.StudentMapper;
import az.developia.librarian.repository.LibrarianRepository;
import az.developia.librarian.repository.UserRepository;
import az.developia.librarian.service.JWTService;
import az.developia.librarian.service.StudentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private LibrarianMapper librarianMapper;

    @Autowired
    private StudentMapper studentMapper;
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;


    public StudentServiceImpl(PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
//                User user = userRepository.findByUsername(username);
//                if (user == null) {
//                    throw new UsernameNotFoundException("User not found with username: " + username);
//                }
//                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
            }
        };
    }

    @Override
    public JwtAuthenticationResponse signupForLibrarian(UserRequest signUpRequest) throws MessagingException {
        User user = new User();
        System.out.println("The password is: => "+signUpRequest.getPassword());

        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setCreatedAt(LocalDateTime.now());
        User savedUser= userRepository.save(user);



        var jwt = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), savedUser);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;


    }

    @Override
    public JwtAuthenticationResponse signupForStudent(UserRequest signUpRequest) throws MessagingException {

        User user = new User();
        System.out.println("The password is: => "+signUpRequest.getPassword());

        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setCreatedAt(LocalDateTime.now());
        User savedUser= userRepository.save(user);



        var jwt = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), savedUser);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) throws IllegalArgumentException{
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;    }



    // Delete a student by ID
    public void deleteStudent(Integer studentId) {
        // Check if the user exists and is a student
        Optional<User> userOptional = userRepository.findById(studentId);
        if (userOptional.isEmpty() || !userOptional.get().getRole().equals("USER")) {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }

        // Delete the student
        userRepository.deleteById(studentId);
    }


    // Edit a student
    public User editStudent(Integer studentId, UserRequest updatedStudent) {
        // Check if the user exists and is a student
        Optional<User> userOptional = userRepository.findById(studentId);
        if (userOptional.isEmpty() || !userOptional.get().getRole().equals("USER")) {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }

        // Get the existing student
        User existingStudent = userOptional.get();

        // Update the fields (only non-null fields)
        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
        }
        if (updatedStudent.getEmail() != null) {
            existingStudent.setEmail(updatedStudent.getEmail());
        }
        if (updatedStudent.getPassword() != null) {
            existingStudent.setPassword(updatedStudent.getPassword()); // Ensure password is hashed
        }
        existingStudent.setUpdatedAt(LocalDateTime.now());

        // Save the updated student
        return userRepository.save(existingStudent);
    }


    // Search for students by name or email
    public List<User> searchStudents(String name, String email) {
        if (name != null && !name.isEmpty()) {
            return userRepository.findByNameContainingIgnoreCaseAndRole(name, "USER");
        } else if (email != null && !email.isEmpty()) {
            return userRepository.findByEmailContainingIgnoreCaseAndRole(email, "USER");
        } else {
            // If no search criteria are provided, return all students
            return userRepository.findByRole("USER");
        }
    }

}
