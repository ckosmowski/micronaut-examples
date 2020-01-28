package de.ksmwsk.deleteall;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;

import javax.persistence.Table;

@MappedEntity(namingStrategy = NamingStrategies.Raw.class)
@Table(name="Book")
public class Book {

    @EmbeddedId
    private BookId id;

    private String author;

    public Book(){}

    public Book(BookId id) {
        this.id = id;
    }

    public BookId getId() {
        return id;
    }

    public void setId(BookId id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
