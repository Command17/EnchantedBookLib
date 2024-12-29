package com.github.command17.enchantedbooklib.api.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public class RegisterVillagerTradeEvent extends Event {
    private final TradeRegistrar registrar;

    public RegisterVillagerTradeEvent(TradeRegistrar registrar) {
        this.registrar = registrar;
    }

    public void register(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade: trades) {
            this.registrar.accept(profession, level, trade);
        }
    }

    @FunctionalInterface
    public interface TradeRegistrar {
        void accept(VillagerProfession profession, int level, VillagerTrades.ItemListing trade);
    }
}
