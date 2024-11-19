package net.rafael.api_library.main_project.Mapper;

import net.rafael.api_library.main_project.Dto.BookDto;
import net.rafael.api_library.main_project.Dto.SearchBookDto;
import net.rafael.api_library.main_project.Models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    private AuthorRepository repository;

    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapAuthorIdToAuthor")
    public abstract Book toEntity(BookDto dto);

    // Método de mapeamento que converte o UUID em um Author
    @Named("mapAuthorIdToAuthor")
    public Author mapAuthorIdToAuthor(UUID authorId) {
        return repository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException("Author not found for id: " + authorId)
        );
    }

    public abstract SearchBookDto toDTO(Book book);
}
