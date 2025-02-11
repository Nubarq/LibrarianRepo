package az.developia.librarian.mapper;

import az.developia.librarian.dto.request.BookRequest;
import az.developia.librarian.dto.request.BorrowedBookRequest;
import az.developia.librarian.dto.response.BookResponse;
import az.developia.librarian.dto.response.BorrowedBookResponse;
import az.developia.librarian.entity.Book;
import az.developia.librarian.entity.BorrowedBook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BorrowedBookMapper {

    BorrowedBook mapBorrowedBookRequestDTOToBorrowedBookEntity(BorrowedBookRequest borrowedBookRequest);
    BorrowedBookResponse mapBorrowedBookEntityToBorrowedBookResponseDTO(BorrowedBook borrowedBook);
}
