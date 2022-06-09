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
    public void savesAndRetrievesSongs() {
        SongEntity songEntity = new SongEntity();
        songEntity.setSongHash("song_1");
        songEntity.setName("Don't Worry, be happy");

        songRepository.save(songEntity); //E11000 duplicate key error collectio on sencond test execution
        final Optional<SongEntity> song1 = songRepository.findById("song_1");
        assertThat("The retrieved song", song1.isPresent(), is(true));
        assertThat("The song hash", song1.get().getSongHash(), is("song_1"));
    }

    @Test
    public void updatesSongs() {
        SongEntity songEntity = new SongEntity();
        songEntity.setSongHash("song_2");
        songEntity.setName("Don't Worry, be happy");
        songEntity.setSubClass(new SubClass());

        songRepository.update(songEntity); //invalid hexadecimal representation of an ObjectId: [song_2]
        final Optional<SongEntity> song2 = songRepository.findById("song_2");
        assertThat("The retrieved song", song2.isPresent(), is(true));
    }
}
