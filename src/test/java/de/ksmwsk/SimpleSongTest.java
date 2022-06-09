package de.ksmwsk;

import de.ksmwsk.model.SongEntity;
import de.ksmwsk.model.SubClass;
import de.ksmwsk.repo.SongRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

/**
 * The overall goal is to use Strings as keys and:
 * to write to the repository without having to read the entity first
 * (insert if not exist, always overwrite if same id exists)
 */
@MicronautTest(environments = "test", transactional = false)
public class SimpleSongTest {
    @Inject
    private SongRepository songRepository;

    @Test
    //Works with micronaut 3.4.4, breaks with micronaut 3.5.1
    //@SerdeImport is used in Config.java
    //@Introspected is used in Subclass.kt
    //If @Introspected is removed the test works again also with mn 3.5.1
    public void updatesSongs() {
        SongEntity songEntity = new SongEntity();
        songEntity.setSongHash("song_2");
        songEntity.setName("Don't Worry, be happy");
        songEntity.setSubClass(new SubClass());

        songRepository.update(songEntity);
        final Optional<SongEntity> song2 = songRepository.findById("song_2");
        assertThat("The retrieved song", song2.isPresent(), is(true));
    }
}
