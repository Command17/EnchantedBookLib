package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RegistryEvent extends Event {
    private final ResourceKey<? extends Registry<?>> registryKey;
    private final Registry<?> registry;
    private final BiConsumer<ResourceLocation, Supplier<?>> registrar;

    public RegistryEvent(ResourceKey<? extends Registry<?>> registryKey, Registry<?> registry, BiConsumer<ResourceLocation, Supplier<?>> registrar) {
        this.registryKey = registryKey;
        this.registry = registry;
        this.registrar = registrar;
    }

    public<T> Supplier<T> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<T> entry) {
        if (this.registryKey == registryKey) {
            this.registrar.accept(id, entry);
            return entry;
        }

        return null;
    }

    public<T> Supplier<T> register(ResourceLocation id, Supplier<T> entry) {
        this.registrar.accept(id, entry);
        return entry;
    }

    public<T> Supplier<T> register(Registry<T> registry, ResourceLocation id, Supplier<T> entry) {
        if (this.registry == registry) {
            this.registrar.accept(id, entry);
            return entry;
        }

        return null;
    }

    public ResourceKey<? extends Registry<?>> getRegistryKey() {
        return registryKey;
    }

    public Registry<?> getRegistry() {
        return registry;
    }
}
