package net.rafael.api_library.main_project.Dto;

import net.rafael.api_library.main_project.Models.BookGenres;

import java.time.LocalDate;
import java.util.UUID;

public record SearchBookDto(UUID id, String isbn, String title, LocalDate publicationDate, BookGenres genre, Double price, AuthorDTO author) {
}
