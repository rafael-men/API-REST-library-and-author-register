package net.rafael.api_library.main_project.Models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(name = "date_pub")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookGenres genre;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book() {
    }

    public Book(UUID id, String isbn, String title, LocalDate publicationDate, Double price, BookGenres genre, Author author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publicationDate = publicationDate;
        this.price = price;
        this.genre = genre;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BookGenres getGenre() {
        return genre;
    }

    public void setGenre(BookGenres genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(publicationDate, book.publicationDate) && genre == book.genre && Objects.equals(price, book.price) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, publicationDate, genre, price, author);
    }
}

