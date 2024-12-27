package com.github.command17.enchantedbooklib.api.config;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class LibConfigs {
    private static final HashMap<ResourceLocation, ConfigData> CONFIG_MAP = new HashMap<>();
    private static final HashMap<ResourceLocation, ConfigData> SYNCABLE_CONFIG_MAP = new HashMap<>();

    public static<T extends ConfigData> T registerConfig(@NotNull ResourceLocation id, @NotNull T config) {
        if (!CONFIG_MAP.containsKey(id)) {
            CONFIG_MAP.put(id, config);

            if (config.hasSyncableEntries()) {
                SYNCABLE_CONFIG_MAP.put(id, config);
            }
        } else {
            throw new IllegalStateException("Config '" + id + "' was already registered!");
        }

        try {
            if (config.load()) {
                config.validate();
                config.save();
            }
        } catch (InvalidConfigException e) {
            throw new RuntimeException(e);
        }

        config.setId(id);

        return config;
    }

    public static ImmutableList<ConfigData> getSyncableConfigs() {
        return ImmutableList.copyOf(SYNCABLE_CONFIG_MAP.values());
    }

    public static ImmutableList<ConfigData> getConfigs() {
        return ImmutableList.copyOf(CONFIG_MAP.values());
    }

    @Nullable
    public static ConfigData getConfig(@NotNull ResourceLocation id) {
        return CONFIG_MAP.get(id);
    }
}
