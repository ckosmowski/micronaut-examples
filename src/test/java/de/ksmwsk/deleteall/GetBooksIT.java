package de.ksmwsk.deleteall;

import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Stream;

@MicronautTest()
public class GetBooksIT {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private JdbcOperations jdbcOperations;


    @Test
    public void readsBooksWithOpenResultset() {

        for (int i = 0; i <=10; i++) {
            Book book = new Book(i + "");
            bookRepository.save(book);
        }

        final Stream<Map<String, Object>> openStream = bookRepository.findAllAndStream();
        openStream.forEach(map -> {
            System.out.println(map);
        });
    }


}
