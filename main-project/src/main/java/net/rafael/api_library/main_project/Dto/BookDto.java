package net.rafael.api_library.main_project.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import net.rafael.api_library.main_project.Models.BookGenres;

import java.time.LocalDate;
import java.util.UUID;

public record BookDto(@NotBlank String isbn, @NotBlank  String title, @NotNull @Past  LocalDate publicationDate, BookGenres genre, Double price,
                      @NotNull UUID authorId) {
}
