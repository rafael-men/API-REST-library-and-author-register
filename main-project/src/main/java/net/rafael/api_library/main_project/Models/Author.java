package net.rafael.api_library.main_project.Models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
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
    private Date birth_date;

    @Column(name = "Origin",nullable = false)
    private String from;

    public Author() {
        //Empty Constructor
    }

    public Author(UUID id, String name, Date birth_date, String from) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.from = from;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(birth_date, author.birth_date) && Objects.equals(from, author.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birth_date, from);
    }
}
