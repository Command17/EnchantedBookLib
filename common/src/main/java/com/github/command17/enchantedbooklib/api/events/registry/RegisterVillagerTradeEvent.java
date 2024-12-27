package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.github.command17.enchantedbooklib.api.registry.VillagerTradeRegistry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public class RegisterVillagerTradeEvent extends Event {
    public void registerVillagerTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        VillagerTradeRegistry.registerVillagerTrade(profession, level, trades);
    }

    public void registerWanderingTraderTrade(boolean rare, VillagerTrades.ItemListing... trades) {
        VillagerTradeRegistry.registerWanderingTraderTrade(rare, trades);
    }
}
