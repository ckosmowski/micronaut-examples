package de.ksmwsk;

import de.ksmwsk.model.Artist;
import de.ksmwsk.model.SongEntity;
import de.ksmwsk.model.SongQuery;
import de.ksmwsk.repo.SongRepository;
import de.ksmwsk.repo.SongSpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
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

        songRepository.update(songEntity); //invalid hexadecimal representation of an ObjectId: [song_2]
        final Optional<SongEntity> song2 = songRepository.findById("song_2");
        assertThat("The retrieved song", song2.isPresent(), is(true));
    }

    @Test
    public void savesAndRetrievesSongsSpec() {
        SongEntity songEntity = new SongEntity();
        songEntity.setSongHash("song_3");
        songEntity.setName("Let it MongoDB");

        songRepository.save(songEntity);
        SongQuery songQuery = new SongQuery("song_3", null);
        final List<SongEntity> songs = songRepository.findAll(SongSpecification.INSTANCE.matchesQuery(List.of(songQuery)));
        Optional<SongEntity> song3 = Optional.ofNullable(songs.get(0));
        assertThat("The retrieved song", song3.isPresent(), is(true));
        assertThat("The song hash", song3.get().getSongHash(), is("song_3"));
    }

    @Test
    public void savesAndRetrievesSongsNestedSpec() {
        SongEntity songEntity = new SongEntity();
        songEntity.setSongHash("song_4");
        songEntity.setName("Test asured");
        Artist artist = new Artist("The Testers");
        songEntity.setArtist(artist);

        songRepository.save(songEntity);
        SongQuery songQuery = new SongQuery(null, "The Testers");
        final List<SongEntity> songs = songRepository.findAll(SongSpecification.INSTANCE.matchesNestedQuery(List.of(songQuery)));
        //Cannot query entity [SongEntity] on non-existent property: artist.name
        Optional<SongEntity> song3 = Optional.ofNullable(songs.get(0));
        assertThat("The retrieved song", song3.isPresent(), is(true));
        assertThat("The song hash", song3.get().getSongHash(), is("song_4"));
        assertThat("The artist", song3.get().getArtist().getName(), is("The Testers"));
    }

}
