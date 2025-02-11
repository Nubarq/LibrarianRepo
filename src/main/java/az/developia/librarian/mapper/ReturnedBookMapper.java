package az.developia.librarian.mapper;

import az.developia.librarian.dto.request.BookRequest;
import az.developia.librarian.dto.request.ReturnedBookRequest;
import az.developia.librarian.dto.response.BookResponse;
import az.developia.librarian.dto.response.ReturnedBookResponse;
import az.developia.librarian.entity.Book;
import az.developia.librarian.entity.ReturnedBook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReturnedBookMapper {
    ReturnedBook mapReturnedBookRequestDTOToReturnedBookEntity(ReturnedBookRequest returnedBookRequest);
    ReturnedBookResponse mapReturnedBookEntityToReturnedBookResponseDTO(ReturnedBook returnedBook);
}
