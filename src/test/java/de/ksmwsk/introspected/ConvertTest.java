package de.ksmwsk.introspected;

import de.ksmwsk.MovieApplication;
import io.micronaut.core.convert.ArgumentConversionContext;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

@MicronautTest(application = MovieApplication.class)
public class ConvertTest {

    @Test
    public void convertsNonIntrospected() {
        final ArgumentConversionContext<Movie> context = ConversionContext.of(Movie.class);

        Map<String, String> sourceData = new HashMap<>();
        sourceData.put("name", "Pulp Fiction");
        sourceData.put("publishingDate", "1994-11-03T00:00");

        final Optional<Movie> movie = ConversionService.SHARED.convert(sourceData, context);
        assertThat("Converted Movie", movie.isPresent(), is(true));
    }

    @Test
    /** Does not work because of DateTimeFormatter.RFC_1123_DATE_TIME beeing the default in
     * io.micronaut.runtime.converters.time.TimeConverterRegistrar#resolveFormatter
     * which effectively is used here
     * /io/micronaut/core/reflect/InstantiationUtils.java:104
     */
    public void convertsIntrospected() {
        final ArgumentConversionContext<MovieIntrospected> context = ConversionContext.of(MovieIntrospected.class);

        Map<String, String> sourceData = new HashMap<>();
        sourceData.put("name", "Pulp Fiction");
        sourceData.put("publishingDate", "1994-11-03T00:00");

        final Optional<MovieIntrospected> movie = ConversionService.SHARED.convert(sourceData, context);
        assertThat("Converted Movie", movie.isPresent(), is(true));
    }

    @Test
    public void convertsIntrospectedWithFormatAndSeconds() {
        final ArgumentConversionContext<MovieIntrospectedFormat> context = ConversionContext.of(MovieIntrospectedFormat.class);

        Map<String, String> sourceData = new HashMap<>();
        sourceData.put("name", "Pulp Fiction");
        sourceData.put("publishingDate", "1994-11-03T00:00:00");

        final Optional<MovieIntrospectedFormat> movie = ConversionService.SHARED.convert(sourceData, context);
        assertThat("Converted Movie", movie.isPresent(), is(true));
    }

    /** Our desired workaround.
     * Does not work for us because we have no chance to make the seconds optional
     * in the @Format annotation**/
    @Test
    public void convertsIntrospectedWithFormatWithoutSeconds() {
        final ArgumentConversionContext<MovieIntrospectedFormat> context = ConversionContext.of(MovieIntrospectedFormat.class);

        Map<String, String> sourceData = new HashMap<>();
        sourceData.put("name", "Pulp Fiction");
        sourceData.put("publishingDate", "1994-11-03T00:00");

        final Optional<MovieIntrospectedFormat> movie = ConversionService.SHARED.convert(sourceData, context);
        assertThat("Converted Movie", movie.isPresent(), is(true));
    }
}
