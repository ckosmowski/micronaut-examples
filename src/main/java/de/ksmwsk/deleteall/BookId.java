package de.ksmwsk.deleteall;

import io.micronaut.data.annotation.Embeddable;

import javax.persistence.Column;

@Embeddable
public class BookId {

    @Column(name = "hash")
    private String hash;
    @Column(name = "name")
    private String name;

    public BookId() {}

    public BookId(String hash, String name) {
        this.hash = hash;
        this.name = name;
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
}
