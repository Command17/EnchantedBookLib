package com.github.command17.enchantedbooklib.api.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.*;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class VillagerTradeRegistryImpl {
    private VillagerTradeRegistryImpl() {}

    private static final HashMap<VillagerProfession, Int2ObjectMap<List<VillagerTrades.ItemListing>>> TRADE_MAP = new HashMap<>();
    private static final List<VillagerTrades.ItemListing> WANDERING_TRADER_TRADES = new ArrayList<>();
    private static final List<VillagerTrades.ItemListing> WANDERING_TRADER_TRADES_RARE = new ArrayList<>();

    public static void registerVillagerTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades2Profession = TRADE_MAP.computeIfAbsent(profession, (i) -> new Int2ObjectOpenHashMap<>());
        List<VillagerTrades.ItemListing> trades2Level = trades2Profession.computeIfAbsent(level, (i) -> new ArrayList<>());
        Collections.addAll(trades2Level, trades);
    }

    public static void registerWanderingTraderTrade(boolean rare, VillagerTrades.ItemListing... trades) {
        if (rare) {
            Collections.addAll(WANDERING_TRADER_TRADES_RARE, trades);
        } else {
            Collections.addAll(WANDERING_TRADER_TRADES, trades);
        }
    }

    @SubscribeEvent
    private static void event(VillagerTradesEvent event) {
        var trades = TRADE_MAP.get(event.getType());

        if (trades != null) {
            trades.int2ObjectEntrySet().forEach(
                    (entry) -> event.getTrades().computeIfAbsent(entry.getIntKey(), (i) -> NonNullList.create()).addAll(entry.getValue()));
        }
    }

    @SubscribeEvent
    private static void event(WandererTradesEvent event) {
        event.getGenericTrades().addAll(WANDERING_TRADER_TRADES);
        event.getRareTrades().addAll(WANDERING_TRADER_TRADES_RARE);
    }
}
