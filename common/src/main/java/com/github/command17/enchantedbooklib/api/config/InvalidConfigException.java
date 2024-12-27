package com.github.command17.enchantedbooklib.api.config;

public class InvalidConfigException extends Exception {
    public InvalidConfigException(String message) {
        super(message);
    }

    public InvalidConfigException(Exception exception) {
        super(exception);
    }

    public InvalidConfigException() {
        super();
    }
}
