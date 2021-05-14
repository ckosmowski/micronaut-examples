package de.ksmwsk.deleteall;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import io.micronaut.runtime.Micronaut;

public class BookApplication {
    public static void main(String args[]) {
        Micronaut.run(BookApplication.class);
    }
}
