package az.developia.librarian.controller;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
import az.developia.librarian.dto.response.StudentResponse;
import az.developia.librarian.service.LibrarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librarians")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping("/registerForLibrarian")
    public ResponseEntity<LibrarianResponse> registrationForLibrarian(@RequestBody @Valid LibrarianRequest request) {
        return librarianService.registerForLibrarian(request);
    }

    @PostMapping("/registerForStudent")
    public ResponseEntity<StudentResponse> registrationForStudent(@RequestBody @Valid StudentRequest request) {
        return librarianService.registerForStudent(request);
    }
}
