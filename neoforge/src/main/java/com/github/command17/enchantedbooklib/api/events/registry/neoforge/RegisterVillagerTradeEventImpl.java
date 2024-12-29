package com.github.command17.enchantedbooklib.api.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegisterVillagerTradeEvent;
import com.google.common.collect.ArrayListMultimap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class RegisterVillagerTradeEventImpl {
    private static final ArrayListMultimap<VillagerProfession, Pair<Integer, VillagerTrades.ItemListing>> TRADE_MAP = ArrayListMultimap.create();

    private static boolean fired = false;

    private RegisterVillagerTradeEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(VillagerTradesEvent event) {
        if (!fired) {
            EventManager.invoke(new RegisterVillagerTradeEvent((profession, level, trade) -> TRADE_MAP.get(profession).add(Pair.of(level, trade))));
            fired = true;
        }

        TRADE_MAP.get(event.getType()).forEach(
                (pair) -> event.getTrades().getOrDefault((int) pair.getKey(), List.of()).add(pair.getValue()));
    }
}
