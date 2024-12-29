package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.function.BiConsumer;

public class RegisterWanderingTraderTradeEvent extends Event {
    private final BiConsumer<Boolean, VillagerTrades.ItemListing> registrar;

    public RegisterWanderingTraderTradeEvent(BiConsumer<Boolean, VillagerTrades.ItemListing> registrar) {
        this.registrar = registrar;
    }

    public void register(boolean rare, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade: trades) {
            this.registrar.accept(rare, trade);
        }
    }
}
