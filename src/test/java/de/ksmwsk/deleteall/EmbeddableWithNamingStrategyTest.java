package de.ksmwsk.deleteall;

import de.ksmwsk.deleteall.Author;
import de.ksmwsk.deleteall.Book;
import de.ksmwsk.deleteall.BookRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

@MicronautTest
public class EmbeddableWithNamingStrategyTest {

    @Inject
    private BookRepository bookRepository;

    @Test
    public void writesAndReadsBooks() {
        Book book = new Book("1");
        Author author = new Author("Jean-Jaques", "Rousseau");
        book.setAuthor(author);

        bookRepository.save(book);

        Optional<Book> bookFromDb = bookRepository.findById("1");
        assertThat("Presence of book from DB", bookFromDb.isPresent(), is(true));

        assertThat("Author is present", bookFromDb.get().getAuthor(), is(true));
        assertThat("Author is Jean-Jaques", bookFromDb.get().getAuthor().getFirstName(), is("Jean-Jaques"));
    }
}
