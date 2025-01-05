package com.github.command17.enchantedbooklib.api.util;

import java.util.function.Supplier;

public final class ModLoaderExecutor {
    private ModLoaderExecutor() {}

    public static void runOn(ModLoaderType loader, Supplier<Runnable> runnable) {
        if (PlatformHelper.getModLoader() == loader) {
            runnable.get().run();
        }
    }
}
