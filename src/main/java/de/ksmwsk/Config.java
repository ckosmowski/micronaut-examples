package de.ksmwsk;

import de.ksmwsk.model.SubClass;
import io.micronaut.context.annotation.Context;
import io.micronaut.serde.annotation.SerdeImport;

@Context
@SerdeImport(SubClass.class)
public class Config {
}
