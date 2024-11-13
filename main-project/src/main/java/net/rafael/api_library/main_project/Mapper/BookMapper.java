package net.rafael.api_library.main_project.Mapper;

import net.rafael.api_library.main_project.Dto.BookDto;
import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    private AuthorRepository repository;

    @Mapping(target = "author", source = "authorId")
    public abstract Book toEntity(BookDto dto);
}
