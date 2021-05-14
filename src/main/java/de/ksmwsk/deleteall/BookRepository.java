package de.ksmwsk.deleteall;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@JdbcRepository(dialect = Dialect.H2)
public abstract class BookRepository implements CrudRepository<Book, String> {

    private JdbcOperations jdbcOperations;

    @Inject
    public BookRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Stream<Map<String, Object>> findAllAndStream() {

        String sql = "SELECT * from BOOK";

        return jdbcOperations.prepareStatement(sql, statement -> {
            statement.setFetchSize(5000);
            ResultSet resultSet = statement.executeQuery();
            return StreamSupport.stream(new ResultsetSpliterator(resultSet), false);
        });
    }
}
