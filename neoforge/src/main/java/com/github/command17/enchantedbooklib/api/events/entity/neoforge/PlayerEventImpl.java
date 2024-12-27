package com.github.command17.enchantedbooklib.api.events.entity.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class PlayerEventImpl {
    private PlayerEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(PlayerLoggedInEvent event) {
        EventManager.invoke(new PlayerEvent.Join((ServerPlayer) event.getEntity()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(PlayerLoggedOutEvent event) {
        EventManager.invoke(new PlayerEvent.Quit((ServerPlayer) event.getEntity()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(PlayerRespawnEvent event) {
        EventManager.invoke(new PlayerEvent.Respawn((ServerPlayer) event.getEntity(), event.getEntity().getRemovalReason()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ItemEntityPickupEvent.Pre event) {
        PlayerEvent.BeforePickUpItem beforePickUpItemEvent = new PlayerEvent.BeforePickUpItem(event.getPlayer(), event.getItemEntity(), event.getItemEntity().getItem());
        EventManager.invoke(beforePickUpItemEvent);

        if (beforePickUpItemEvent.isCanceled()) {
            event.setCanPickup(beforePickUpItemEvent.canPickUp() ? TriState.TRUE : TriState.FALSE);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ItemEntityPickupEvent.Post event) {
        EventManager.invoke(new PlayerEvent.PickUpItem(event.getPlayer(), event.getItemEntity(), event.getItemEntity().getItem()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(ItemTossEvent event) {
        EventManager.invoke(new PlayerEvent.DropItem(event.getPlayer(), event.getEntity()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(AttackEntityEvent event) {
        PlayerEvent.AttackEntity attackEntityEvent = new PlayerEvent.AttackEntity(event.getEntity(), event.getEntity().level(), event.getTarget(), event.getEntity().getUsedItemHand(), null);
        EventManager.invoke(attackEntityEvent);
        event.setCanceled(attackEntityEvent.isCanceled());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(PlayerTickEvent.Pre event) {
        EventManager.invoke(new PlayerEvent.PreTick(event.getEntity()));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(PlayerTickEvent.Post event) {
        EventManager.invoke(new PlayerEvent.PostTick(event.getEntity()));
    }
}
