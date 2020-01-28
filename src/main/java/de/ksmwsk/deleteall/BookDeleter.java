package de.ksmwsk.deleteall;

import io.micronaut.configuration.dbmigration.flyway.event.MigrationFinishedEvent;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.event.ApplicationEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Named
@Context
public class BookDeleter  {

    private static final Logger LOG = LoggerFactory.getLogger(BookDeleter.class);

    private BookRepository bookRepository;

    @Inject
    public BookDeleter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

        Iterable<Book> all = bookRepository.findAll();
        ArrayList<Book> allBooks = new ArrayList<>();
        all.forEach(allBooks::add);
        LOG.debug("Found {} books before deletion", allBooks.size());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Book book1 = new Book(new BookId("1", "In Search of Lost Time"));
        Book book2 = new Book(new BookId("2", "Superfreakonomics"));

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        bookRepository.deleteAll(books);

        all = bookRepository.findAll();
        allBooks = new ArrayList<>();
        all.forEach(allBooks::add);

        LOG.debug("Found {} books after deletion", allBooks.size());
    }

}
