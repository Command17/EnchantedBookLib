package com.github.command17.enchantedbooklib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.KeyMapping;

import java.util.function.Supplier;

public final class KeyMappingRegistry {
    private KeyMappingRegistry() {}

    @ExpectPlatform
    public static Supplier<KeyMapping> registerKeyMapping(Supplier<KeyMapping> keyMapping) {
        throw new AssertionError();
    }
}
