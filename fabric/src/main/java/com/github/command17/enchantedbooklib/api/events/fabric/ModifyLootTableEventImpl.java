package com.github.command17.enchantedbooklib.api.events.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.ModifyLootTableEvent;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.ApiStatus;

public final class ModifyLootTableEventImpl {
    private ModifyLootTableEventImpl() {}

    @ApiStatus.Internal
    public static void register() {
        LootTableEvents.MODIFY.register((key, builder, source, provider) -> EventManager.invoke(new ModifyLootTableEvent(key, new ModifyLootTableContextImpl(builder), source.isBuiltin())));
    }

    public static class ModifyLootTableContextImpl implements ModifyLootTableEvent.ModifyLootTableContext {
        private final LootTable.Builder builder;

        public ModifyLootTableContextImpl(LootTable.Builder builder) {
            this.builder = builder;
        }

        @Override
        public void addPool(LootPool.Builder pool) {
            this.builder.pool(pool.build());
        }
    }
}
