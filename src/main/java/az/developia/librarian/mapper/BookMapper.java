package az.developia.librarian.mapper;
import az.developia.librarian.dto.request.BookRequest;
import az.developia.librarian.dto.response.BookResponse;
import az.developia.librarian.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book mapBookRequestDTOToBookEntity(BookRequest bookRequest);
    BookResponse mapBookEntityToBookResponseDTO(Book book);
}
