package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface BookRepository extends JpaRepository<Book, UUID> {
}
