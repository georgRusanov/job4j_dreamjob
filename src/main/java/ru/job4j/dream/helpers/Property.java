package ru.job4j.dream.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class Property {
    static Properties cfg = new Properties();

    public static String getProperty(String property) {
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(Property.class.getClassLoader()
                                .getResourceAsStream("file.properties"))
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return cfg.getProperty(property);
    }
}
