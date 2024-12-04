package net.rafael.api_library.main_project.Specs;

import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Models.BookGenres;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"),isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("title")), title.toUpperCase());
    }

    public static Specification<Book> genderEqual(BookGenres genre) {
        return (root,query,cb) -> cb.equal(root.get("genre"),genre);
    }
}
