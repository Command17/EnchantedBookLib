package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterEntityLayerEvent extends Event {
    private final BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar;

    public RegisterEntityLayerEvent(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar) {
        this.registrar = registrar;
    }

    public void register(ModelLayerLocation location, Supplier<LayerDefinition> layerDefinition) {
        registrar.accept(location, layerDefinition);
    }
}
