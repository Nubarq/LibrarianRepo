package az.developia.librarian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedBookResponse {
    private LocalDateTime returnedDate;
    private double fineAmount;
}
