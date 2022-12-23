package org.iogame;

public final class StaticSettings {

    public enum Environment {
        DEVELOPMENT,
        PRODUCTION
    }

    public static Environment ENV = Environment.DEVELOPMENT;
}
