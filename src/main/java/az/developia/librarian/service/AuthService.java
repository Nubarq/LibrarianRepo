package az.developia.librarian.service;

import az.developia.librarian.dto.request.SigninRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> signin(SigninRequest request);
}
