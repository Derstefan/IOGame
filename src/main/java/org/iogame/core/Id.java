package org.iogame.core;

import org.iogame.StaticSettings.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static org.iogame.StaticSettings.ENV;

public class Id {

    private static final Map<Class<?>, AtomicLong> LONG_ID_POOL = new HashMap<>();

    private final Object value;

    public static Id generate() {
        return generateForClass(Object.class);
    }

    public static Id generateForClass(Class<?> clazz) {
        Object value = null;
        if (ENV == Environment.DEVELOPMENT) {
            value = LONG_ID_POOL.computeIfAbsent(clazz, __ -> new AtomicLong(0)).getAndIncrement();
        }
        if (ENV == Environment.PRODUCTION) {
            value = UUID.randomUUID();
        }
        if (value == null) {
            throw new IllegalArgumentException(String.format("Environment %s is not registered.", ENV));
        }
        return new Id(value);
    }

    public static Id fromString(String stringValue) {
        Object value = null;
        if (ENV == Environment.DEVELOPMENT) {
            value = Long.valueOf(stringValue);
        }
        if (ENV == Environment.PRODUCTION) {
            value = UUID.fromString(stringValue);
        }
        if (value == null) {
            throw new IllegalArgumentException(String.format("Environment %s is not registered.", ENV));
        }
        return new Id(value);
    }

    Id(Object value) {
        this.value = value;
    }

    public <T> T getValue() {
        return (T) value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Id)) return false;
        return this.value.equals(((Id) obj).value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
