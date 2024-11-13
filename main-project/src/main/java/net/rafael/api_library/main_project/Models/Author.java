package net.rafael.api_library.main_project.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "origin", nullable = false)
    private String from;

    @OneToMany(mappedBy = "author")
    @Column(name = "books")
    private List<Book> Books;

    @CreatedDate
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "user_id")
    private UUID userId;

    public Author() {}

    @JsonCreator
    public Author(UUID id, @JsonProperty("name") String name, LocalDate birthDate, List<Book> books, String from, LocalDateTime registerDate, UUID userId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.Books = books;
        this.from = from;
        this.registerDate = registerDate;
        this.userId = userId;
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

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        this.Books = books;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(birthDate, author.birthDate) && Objects.equals(from, author.from) && Objects.equals(Books, author.Books) && Objects.equals(registerDate, author.registerDate) && Objects.equals(userId, author.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, from, Books, registerDate, userId);
    }
}
