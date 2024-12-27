package com.github.command17.enchantedbooklib.api.util.neoforge;

import com.github.command17.enchantedbooklib.api.util.EnvSide;
import com.github.command17.enchantedbooklib.api.util.ModLoaderType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class PlatformHelperImpl {
    private PlatformHelperImpl() {}

    public static ModLoaderType getModLoader() {
        return ModLoaderType.NEOFORGE;
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.production;
    }

    public static Path getMinecraftPath() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Path getModPath() {
        return FMLPaths.MODSDIR.get();
    }

    public static EnvSide getEnvSide() {
        return EnvSide.fromPlatformSpecific(FMLEnvironment.dist);
    }
}
