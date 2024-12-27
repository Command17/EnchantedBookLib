package com.github.command17.enchantedbooklib.api.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.Arrays;

public final class VillagerTradeRegistryImpl {
    private VillagerTradeRegistryImpl() {}

    public static void registerVillagerTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        TradeOfferHelper.registerVillagerOffers(profession, level, (tradeList) -> tradeList.addAll(Arrays.asList(trades)));
    }

    public static void registerWanderingTraderTrade(boolean rare, VillagerTrades.ItemListing... trades) {
        TradeOfferHelper.registerWanderingTraderOffers(rare ? 2 : 1, (tradeList) -> tradeList.addAll(Arrays.asList(trades)));
    }
}
