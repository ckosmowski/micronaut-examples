package de.ksmwsk.deleteall;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.*;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

@MicronautTest()
public class GetBooksIT {

    @Inject
    private BookRepository bookRepository;

    @Test
    public void readsBooks() {

        for (int i = 0; i <=10; i++) {
            Book book = new Book(i + "");
            book.setTopic("How to Update a Database");
            bookRepository.save(book);
        }

        Iterable<Book> books = bookRepository.findAll();
        assertThat("The books", books, is(not(nullValue())));
        books.forEach(book -> {
            assertThat("The Topic", book.getTopic(), is("How to Update a Database"));
        });

        bookRepository.updateByIdInList(Arrays.asList("1", "3", "8"), "How to fix Regressions");

        books = bookRepository.findAll();
        assertThat("The books", books, is(not(nullValue())));

        AtomicInteger count = new AtomicInteger();
        books.forEach(book -> {
            if (count.get() == 1 ||  count.get() == 3 || count.get() == 8) {
                assertThat("The Topic", book.getTopic(), is("How to fix Regressions"));
            } else {
                assertThat("The Topic", book.getTopic(), is("How to Update a Database"));
            }
            count.getAndIncrement();
        });
    }


}
