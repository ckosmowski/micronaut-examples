package de.ksmwsk.factory;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.context.annotation.Prototype;

@Factory
public class TestFactory {

    @Prototype
    public TestBean createTestBean(@Parameter TestSingleton singleton) {
        return new TestBean(singleton);
    }
}
