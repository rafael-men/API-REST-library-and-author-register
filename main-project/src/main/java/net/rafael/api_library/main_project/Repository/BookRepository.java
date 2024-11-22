package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.id = :bookId")
    Book findBookWithAuthor(@Param("bookId") UUID bookId);
}
