package com.github.command17.enchantedbooklib.api.util;

public final class EnvSideExecutor {
    private EnvSideExecutor() {}

    public static void runOn(EnvSide env, Runnable runnable) {
        if (PlatformHelper.getEnvSide() == env) {
            runnable.run();
        }
    }

    public static void runOnBoth(Runnable clientRunnable, Runnable serverRunnable) {
        runOn(EnvSide.CLIENT, clientRunnable);
        runOn(EnvSide.SERVER, serverRunnable);
    }
}
