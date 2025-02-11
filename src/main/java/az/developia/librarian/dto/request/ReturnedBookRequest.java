package az.developia.librarian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedBookRequest {
    private LocalDateTime returnedDate;
    private double fineAmount;
}
