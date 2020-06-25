package de.ksmwsk.factory;

public class TestBean {
    private TestSingleton singleton;
    public TestBean(TestSingleton singleton) {
        this.singleton = singleton;
    }
}
