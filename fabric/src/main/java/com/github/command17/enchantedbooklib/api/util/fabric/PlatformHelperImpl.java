package com.github.command17.enchantedbooklib.api.util.fabric;

import com.github.command17.enchantedbooklib.api.util.EnvSide;
import com.github.command17.enchantedbooklib.api.util.ModLoaderType;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class PlatformHelperImpl {
    private PlatformHelperImpl() {}

    public static ModLoaderType getModLoader() {
        return ModLoaderType.FABRIC;
    }

    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Path getMinecraftPath() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Path getModPath() {
        return FabricLoader.getInstance().getGameDir().resolve("mods");
    }

    public static EnvSide getEnvSide() {
        return EnvSide.fromPlatformSpecific(FabricLoader.getInstance().getEnvironmentType());
    }
}
