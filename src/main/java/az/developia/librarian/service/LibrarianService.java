package az.developia.librarian.service;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
import org.springframework.http.ResponseEntity;

public interface LibrarianService {

    ResponseEntity<LibrarianResponse> register(LibrarianRequest request);
}
