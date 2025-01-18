package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class RegisterItemTintSourceEvent extends Event {
    private final BiConsumer<ResourceLocation, MapCodec<? extends ItemTintSource>> registrar;

    public RegisterItemTintSourceEvent(BiConsumer<ResourceLocation, MapCodec<? extends ItemTintSource>> registrar) {
        this.registrar = registrar;
    }

    public void register(ResourceLocation id, MapCodec<? extends ItemTintSource> source) {
        this.registrar.accept(id, source);
    }
}
