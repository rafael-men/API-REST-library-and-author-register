package net.rafael.api_library.main_project.Mapper;


import net.rafael.api_library.main_project.Dto.AuthorDTO;
import net.rafael.api_library.main_project.Models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "birthDate",target = "birthDate")
    @Mapping(source = "from", target = "from")
    Author toEntity(AuthorDTO author);

    AuthorDTO toDTO(Author author);
}
