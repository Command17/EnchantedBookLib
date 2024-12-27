package com.github.command17.enchantedbooklib.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public final class VillagerTradeRegistry {
    private VillagerTradeRegistry() {}

    @ExpectPlatform
    public static void registerVillagerTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerWanderingTraderTrade(boolean rare, VillagerTrades.ItemListing... trades) {
        throw new AssertionError();
    }
}
