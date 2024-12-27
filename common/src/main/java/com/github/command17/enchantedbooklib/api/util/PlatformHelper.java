package com.github.command17.enchantedbooklib.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public final class PlatformHelper {
    private PlatformHelper() {}

    @ExpectPlatform
    public static ModLoaderType getModLoader() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isDevelopmentEnvironment() {
        throw new AssertionError();
    }

    public static boolean isProductionEnvironment() {
        return !isDevelopmentEnvironment();
    }

    @ExpectPlatform
    public static Path getMinecraftPath() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getConfigPath() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getModPath() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static EnvSide getEnvSide() {
        throw new AssertionError();
    }
}
