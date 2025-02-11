package az.developia.librarian.mapper;

import az.developia.librarian.dto.request.UserRequest;
import az.developia.librarian.dto.response.UserResponse;
import az.developia.librarian.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    User mapStudentRequestDTOToStudentdEntity(UserRequest userRequest);
    UserResponse mapStudentEntityToStudentResponseDTO(User user);
}
