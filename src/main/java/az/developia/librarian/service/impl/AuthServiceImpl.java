package az.developia.librarian.service.impl;

import az.developia.librarian.dto.ExceptionDTO;
import az.developia.librarian.dto.request.SigninRequest;
import az.developia.librarian.dto.response.JwtAuthenticationResponse;
import az.developia.librarian.entity.User;
import az.developia.librarian.repository.UserRepository;
import az.developia.librarian.service.AuthService;
import az.developia.librarian.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> signin(SigninRequest loginReq) {
        log.info("authenticate method started by: {}", loginReq.getEmail());
        try {
            // Authenticate user
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(),
                            loginReq.getPassword()));
            log.info("authentication details: {}", authentication);

            // Extract email and roles from authentication
            String email = authentication.getName();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // Create JWT token
            User client = new User(email, "");
            String token = jwtUtil.createToken(client);

            // Add token to headers
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            // Prepare the response with roles included
            JwtAuthenticationResponse loginRes = new JwtAuthenticationResponse(email, token, roles);
            log.info("user: {} logged in", client.getEmail());

            // Return response with the token and roles
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginRes);

        } catch (BadCredentialsException e) {
            ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Invalid username or password");
            log.error("Error due to {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
        } catch (Exception e) {
            ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            log.error("Error due to {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
        }
    }

}
