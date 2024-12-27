package com.github.command17.enchantedbooklib.api.events.entity.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.LivingEntityEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public final class LivingEntityEventImpl {
    private LivingEntityEventImpl() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LivingDeathEvent event) {
        LivingEntityEvent.Death deathEvent = new LivingEntityEvent.Death(event.getEntity(), event.getSource());
        EventManager.invoke(deathEvent);
        event.setCanceled(deathEvent.isCanceled());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(LivingIncomingDamageEvent event) {
        LivingEntityEvent.Damage damageEvent = new LivingEntityEvent.Damage(event.getEntity(), event.getSource(), event.getAmount());
        EventManager.invoke(damageEvent);
        event.setCanceled(damageEvent.isCanceled());
    }
}
