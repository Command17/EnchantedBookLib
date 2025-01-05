package com.github.command17.enchantedbooklib.api.util;

import java.util.function.Supplier;

public final class EnvSideExecutor {
    private EnvSideExecutor() {}

    public static void runOn(EnvSide env, Supplier<Runnable> runnable) {
        if (PlatformHelper.getEnvSide() == env) {
            runnable.get().run();
        }
    }

    public static void runOnBoth(Supplier<Runnable> clientRunnable, Supplier<Runnable> serverRunnable) {
        runOn(EnvSide.CLIENT, clientRunnable);
        runOn(EnvSide.SERVER, serverRunnable);
    }
}
