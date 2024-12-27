package com.github.command17.enchantedbooklib.api.events;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.ApiStatus;

public class ModifyLootTableEvent extends Event {
    private final ResourceKey<LootTable> key;
    private final ModifyLootTableContext context;
    private final boolean builtIn;

    public ModifyLootTableEvent(ResourceKey<LootTable> key, ModifyLootTableContext context, boolean builtIn) {
        this.key = key;
        this.context = context;
        this.builtIn = builtIn;
    }

    public ResourceKey<LootTable> getKey() {
        return key;
    }

    public ModifyLootTableContext getContext() {
        return context;
    }

    public boolean isBuiltIn() {
        return builtIn;
    }

    @ApiStatus.NonExtendable
    public interface ModifyLootTableContext {
        void addPool(LootPool.Builder pool);
    }
}
