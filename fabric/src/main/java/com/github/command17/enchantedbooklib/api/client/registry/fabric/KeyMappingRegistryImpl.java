package com.github.command17.enchantedbooklib.api.client.registry.fabric;

import com.google.common.base.Suppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class KeyMappingRegistryImpl {
    private KeyMappingRegistryImpl() {}

    public static Supplier<KeyMapping> registerKeyMapping(Supplier<KeyMapping> keyMapping) {
        return Suppliers.memoize(() -> KeyBindingHelper.registerKeyBinding(keyMapping.get()));
    }
}
