package com.github.command17.enchantedbooklib.api.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface IRegistrySupplier<T> extends Supplier<T> {
    ResourceLocation getId();
    ResourceKey<T> getKey();
    Holder<T> getHolder();
}
