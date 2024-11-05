package net.rafael.api_library.main_project.Models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "birthdate",nullable = false)
    private LocalDate birth_date;

    @Column(name = "Origin",nullable = false)
    private String from;

    @OneToMany(mappedBy = "AuthorId")
    @Column(name = "books")
    private List<Book> Books;

    public Author() {
        //Empty Constructor
    }

    public Author(UUID id, String name, LocalDate birth_date, String from,List<Book> Books) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.from = from;
        this.Books = Books;
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

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setBooks(List<Book> Books) {
        this.Books = Books;
    }
}
