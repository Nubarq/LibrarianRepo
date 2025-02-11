package az.developia.librarian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
