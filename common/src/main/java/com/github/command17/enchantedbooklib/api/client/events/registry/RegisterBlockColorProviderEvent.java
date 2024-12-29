package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterBlockColorProviderEvent extends Event {
    private final BiConsumer<BlockColor, Block> registrar;

    public RegisterBlockColorProviderEvent(BiConsumer<BlockColor, Block> registrar) {
        this.registrar = registrar;
    }

    @SafeVarargs
    public final void register(BlockColor color, Supplier<Block>... blocks) {
        for (Supplier<Block> block: blocks) {
            this.registrar.accept(color, block.get());
        }
    }

    public void register(BlockColor color, Block... blocks) {
        for (Block block: blocks) {
            this.registrar.accept(color, block);
        }
    }
}
