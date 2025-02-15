package az.developia.librarian.service;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.request.StudentRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
import az.developia.librarian.dto.response.StudentResponse;
import org.springframework.http.ResponseEntity;

public interface LibrarianService {

    ResponseEntity<LibrarianResponse> registerForLibrarian(LibrarianRequest request);

    ResponseEntity<StudentResponse> registerForStudent(StudentRequest request);
}
