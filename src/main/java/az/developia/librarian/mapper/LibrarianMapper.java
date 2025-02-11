package az.developia.librarian.mapper;

import az.developia.librarian.dto.request.LibrarianRequest;
import az.developia.librarian.dto.response.LibrarianResponse;
import az.developia.librarian.entity.Librarian;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibrarianMapper {
    Librarian mapLibrarianRequestDTOToLibrarianEntity(LibrarianRequest librarianRequest);
    LibrarianResponse mapLibrarianEntityToLibrarianResponseDTO(Librarian librarian);

}
