package com.github.command17.enchantedbooklib.api.client.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterRendererEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RegisterRendererEventImpl {
    private RegisterRendererEventImpl() {}

    @SuppressWarnings("unchecked")
    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(EntityRenderersEvent.RegisterRenderers event) {
        EventManager.invoke(new RegisterRendererEvent(
                (entityType, renderer) -> event.registerEntityRenderer(entityType, (EntityRendererProvider<Entity>) renderer),
                (blockEntityType, renderer) -> event.registerBlockEntityRenderer(blockEntityType, (BlockEntityRendererProvider<BlockEntity>) renderer)));
    }
}
