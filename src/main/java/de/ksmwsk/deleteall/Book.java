package de.ksmwsk.deleteall;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@MappedEntity(namingStrategy = NamingStrategies.Raw.class)
@Table(name="Book")
public class Book {

    @Id
    private String id;

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
