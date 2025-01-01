package com.github.command17.testmod.event;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.ServerLifecycleEvent;
import com.github.command17.enchantedbooklib.api.events.command.RegisterCommandEvent;
import com.github.command17.enchantedbooklib.api.events.entity.LivingEntityEvent;
import com.github.command17.enchantedbooklib.api.events.level.BlockEvent;
import com.github.command17.testmod.TestMod;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public final class ModEvents {
    private ModEvents() {}

    @EventListener
    private static void onBreakBlock(BlockEvent.Break event) {
        Player player = event.getPlayer();

        if (player.isShiftKeyDown()) {
            TestMod.LOGGER.info("{} broke a block!", player.getGameProfile().getName());
            event.cancel();
        }
    }

    @EventListener
    private static void onPlaceBlock(BlockEvent.Place event) {
        Entity placer = event.getPlacer();

        if (placer != null && placer.isShiftKeyDown()) {
            TestMod.LOGGER.info("{} placed a block!", placer.getName().getString());
            event.cancel();
        }
    }

    @EventListener
    private static void onEntityHurt(LivingEntityEvent.Damage event) {
        TestMod.LOGGER.info("{} got hurt!", event.getEntity().getName().getString());
    }

    @EventListener
    private static void onEntityDeath(LivingEntityEvent.Death event) {
        TestMod.LOGGER.info("{} died!", event.getEntity().getName().getString());
    }

    @EventListener
    private static void onRegisterCommands(RegisterCommandEvent event) {
        event.getDispatcher().register(Commands.literal("testsay").executes((context) -> {
            context.getSource().sendSystemMessage(Component.literal("You used '/testsay'! I will now say test: \"test\""));
            return 1;
        }));
    }

    public static class InnerEvents {
        @EventListener
        private void onServerStart(ServerLifecycleEvent.Started event) {
            TestMod.LOGGER.info("Server Started!");
        }

        @EventListener
        private void onServerStop(ServerLifecycleEvent.Stopped event) {
            TestMod.LOGGER.info("Server Stopped!");
        }
    }

    public static void register() {
        EventManager.registerListeners(ModEvents.class);
        EventManager.registerListeners(new InnerEvents());
    }
}
