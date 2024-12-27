package com.github.command17.enchantedbooklib.api.events.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.ModifyLootTableEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import java.lang.reflect.Field;
import java.util.List;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class ModifyLootTableEventImpl {
    private ModifyLootTableEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LootTableLoadEvent event) {
        EventManager.invoke(new ModifyLootTableEvent(ResourceKey.create(Registries.LOOT_TABLE, event.getName()), new ModifyLootTableContextImpl(event.getTable()), true));
    }

    public static class ModifyLootTableContextImpl implements ModifyLootTableEvent.ModifyLootTableContext {
        private final List<LootPool> pools;

        @SuppressWarnings("unchecked")
        public ModifyLootTableContextImpl(LootTable table) {
            List<LootPool> pools = null;

            try {
                Field field = LootTable.class.getDeclaredField("pools");
                field.setAccessible(true);

                try {
                    pools = (List<LootPool>) field.get(table);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                for (Field field: LootTable.class.getDeclaredFields()) {
                    if (field.getType().equals(List.class)) {
                        field.setAccessible(true);

                        try {
                            pools = (List<LootPool>) field.get(table);
                        } catch (Exception f) {
                            throw new RuntimeException(f);
                        }
                    }
                }

                if (pools == null) {
                    throw new RuntimeException("Could not find pools field in LootTable!");
                }
            }

            this.pools = pools;
        }

        @Override
        public void addPool(LootPool.Builder pool) {
            this.pools.add(pool.build());
        }
    }
}
