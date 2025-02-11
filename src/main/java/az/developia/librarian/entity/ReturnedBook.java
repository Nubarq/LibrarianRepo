package az.developia.librarian.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReturnedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // The book being returned

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User user; // The student returning the book

    private LocalDateTime returnedDate;
    private double fineAmount;
}
