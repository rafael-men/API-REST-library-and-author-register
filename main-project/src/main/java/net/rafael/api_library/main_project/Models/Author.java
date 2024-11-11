package net.rafael.api_library.main_project.Models;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "birthdate",nullable = false)
    private LocalDate birthDate;

    @Column(name = "Origin",nullable = false)
    private String from;

    @OneToMany(mappedBy = "AuthorId")
    @Column(name = "books")
    private List<Book> Books;

    @CreatedDate
    @Column(name = "register_date")
    private LocalDateTime Register_date;

    private UUID user_id;

    public Author() {
    }

    public Author(UUID id, String name, LocalDate birthDate, List<Book> books, String from, LocalDateTime register_date, UUID user_id) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        Books = books;
        this.from = from;
        Register_date = register_date;
        this.user_id = user_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDateTime getRegister_date() {
        return Register_date;
    }

    public void setRegister_date(LocalDateTime register_date) {
        Register_date = register_date;
    }

    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(birthDate, author.birthDate) && Objects.equals(from, author.from) && Objects.equals(Books, author.Books) && Objects.equals(Register_date, author.Register_date) && Objects.equals(user_id, author.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, from, Books, Register_date, user_id);
    }
}
