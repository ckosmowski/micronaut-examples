package de.ksmwsk.deleteall;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.model.naming.NamingStrategies;

import javax.persistence.Id;
import javax.persistence.Table;

@MappedEntity(namingStrategy = NamingStrategies.Raw.class)
@Table(name="Book")
public class Book {

    @Id
    private String id;
    private String topic;

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
