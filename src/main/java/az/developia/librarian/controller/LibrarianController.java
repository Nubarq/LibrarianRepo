package az.developia.librarian.controller;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
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

    @PostMapping("/register")
    public ResponseEntity<LibrarianResponse> registration(@RequestBody @Valid LibrarianRequest request) {
        return librarianService.register(request);
    }
}
