package az.developia.librarian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
