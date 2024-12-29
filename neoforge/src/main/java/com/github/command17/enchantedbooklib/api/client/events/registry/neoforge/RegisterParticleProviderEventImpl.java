package com.github.command17.enchantedbooklib.api.client.events.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.client.events.registry.RegisterParticleProviderEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RegisterParticleProviderEventImpl {
    private RegisterParticleProviderEventImpl() {}

    @SuppressWarnings("unchecked")
    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void event(RegisterParticleProvidersEvent event) {
        EventManager.invoke(new RegisterParticleProviderEvent((particleType, provider) -> event.registerSpecial((ParticleType<ParticleOptions>) particleType, (ParticleProvider<ParticleOptions>) provider)));
    }
}
