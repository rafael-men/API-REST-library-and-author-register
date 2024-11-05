package net.rafael.api_library.main_project.Dto;

import net.rafael.api_library.main_project.Models.Author;

import java.time.LocalDate;

public record AuthorDTO(String name, LocalDate birth_date,String from) {
    public Author mapForAuthor() {
        Author author = new Author();
        author.setName(this.name);
        author.setBirth_date(this.birth_date);
        author.setFrom(this.from);
        return author;
    }
}
