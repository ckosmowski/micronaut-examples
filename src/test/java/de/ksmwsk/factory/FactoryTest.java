package de.ksmwsk.factory;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.*;
import static org.rnorth.visibleassertions.VisibleAssertions.assertThat;

@MicronautTest
public class FactoryTest {

    @Inject
    TestBean testBean;

    @Test
    public void beanTest() {
        assertThat("the bean", testBean, is(not(nullValue())));
    }
}
