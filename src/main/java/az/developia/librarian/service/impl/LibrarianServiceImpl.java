package az.developia.librarian.service.impl;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
import az.developia.librarian.entity.Authority;
import az.developia.librarian.entity.Librarian;
import az.developia.librarian.entity.User;
import az.developia.librarian.exeption.CustomException;
import az.developia.librarian.mapper.LibrarianMapper;
import az.developia.librarian.repository.LibrarianRepository;
import az.developia.librarian.repository.UserRepository;
import az.developia.librarian.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Override
    public ResponseEntity<LibrarianResponse> register(LibrarianRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User already exists");
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        Authority authority = new Authority("LIBRARIAN");
        Set<Authority> authoritySet = Set.of(authority);
        user.setAuthorities(authoritySet);
        userRepository.save(user);

        Librarian librarian = new Librarian();
        modelMapper.map(request, librarian);
        librarian.setUser(user);
        librarianRepository.save(librarian);

        LibrarianResponse response = new LibrarianResponse();
        modelMapper.map(librarian, response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
