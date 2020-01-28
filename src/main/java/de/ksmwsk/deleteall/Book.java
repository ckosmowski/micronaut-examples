package de.ksmwsk.deleteall;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;

import javax.persistence.Table;

@MappedEntity(namingStrategy = NamingStrategies.Raw.class)
@Table(name="Book")
public class Book {

    @Id
    private String hash;
    private String name;

    private String author;

    public Book(){}

    public Book(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
