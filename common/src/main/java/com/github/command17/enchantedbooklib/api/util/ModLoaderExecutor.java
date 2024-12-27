package com.github.command17.enchantedbooklib.api.util;

public final class ModLoaderExecutor {
    private ModLoaderExecutor() {}

    public static void runOn(ModLoaderType loader, Runnable runnable) {
        if (PlatformHelper.getModLoader() == loader) {
            runnable.run();
        }
    }
}
