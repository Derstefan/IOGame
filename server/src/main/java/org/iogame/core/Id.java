package org.iogame.core;

import org.jetbrains.annotations.TestOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Id {

    private static final Map<Class<?>, AtomicLong> LONG_ID_POOL = new HashMap<>();

    private final long id;

    public static Id generate() {
        return generateForClass(Object.class);
    }

    public static Id generateForClass(Class<?> clazz) {
        var newId = LONG_ID_POOL.computeIfAbsent(clazz, __ -> new AtomicLong(0)).getAndIncrement();
        return new Id(newId);
    }

    public static Id fromString(String stringValue) {
        return new Id(Long.parseLong(stringValue));
    }

    Id(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Id)) return false;
        return this.id == ((Id) obj).id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }

    @TestOnly
    public static void clear() {
        LONG_ID_POOL.clear();
    }
}
