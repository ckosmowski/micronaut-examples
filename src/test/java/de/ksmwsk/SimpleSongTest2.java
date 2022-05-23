package de.ksmwsk;

import de.ksmwsk.model.SongEntity2;
import de.ksmwsk.repo.SongRepository2;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

/**
 * The overall goal is to use Strings as keys and:
 * to write to the repository without having to read the entity first
 * (insert if not exist, always overwrite if same id exists)
 *
 * This version uses ObjectIds (which we would rather not use because our
 * ids have 32 bytes while ObjectIds only allow 24)
 *
 */
@MicronautTest(environments = "test", transactional = false)
public class SimpleSongTest2 {
    @Inject
    private SongRepository2 songRepository;

    @Test
    public void savesAndRetrievesSongs() {
        ObjectId id = new ObjectId("ffffffffffffffffffffffff");
        SongEntity2 songEntity = new SongEntity2();
        songEntity.setId(id);
        songEntity.setName("Don't Worry, be happy");

        songRepository.save(songEntity); //E11000 duplicate key error collectio on sencond test execution
        final Optional<SongEntity2> song1 = songRepository.findById(id);
        assertThat("The retrieved song", song1.isPresent(), is(true));
    }

    @Test
    public void updatesSongs() {
        ObjectId id = new ObjectId("eeeeeeeeeeeeeeeeeeeeeeee");

        SongEntity2 songEntity = new SongEntity2();
        songEntity.setId(id);
        songEntity.setName("Don't Worry, be happy");

        songRepository.update(songEntity);
        final Optional<SongEntity2> song2 = songRepository.findById(id);
        assertThat("The retrieved song", song2.isPresent(), is(true)); //assertionError since document is not inserted
    }
}
