package de.ksmwsk.deleteall;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public abstract class BookRepository implements CrudRepository<Book, String> {
    abstract void updateByIdInList(List<String> id, String topic);
}
