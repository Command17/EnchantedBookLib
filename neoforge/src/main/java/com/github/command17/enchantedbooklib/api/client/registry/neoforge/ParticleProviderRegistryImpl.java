package com.github.command17.enchantedbooklib.api.client.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ParticleProviderRegistryImpl {
    private ParticleProviderRegistryImpl() {}

    private static final ConcurrentHashMap<Supplier<? extends ParticleType<?>>, ParticleProvider<?>> PROVIDER_MAP = new ConcurrentHashMap<>();

    public static <T extends ParticleOptions> void register(Supplier<? extends ParticleType<T>> particleType, ParticleProvider<T> provider) {
        PROVIDER_MAP.put(particleType, provider);
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    private static void event(RegisterParticleProvidersEvent event) {
        PROVIDER_MAP.forEach(
                (particleType, provider) -> event.registerSpecial((ParticleType<ParticleOptions>) particleType.get(), (ParticleProvider<ParticleOptions>) provider));
    }
}

















