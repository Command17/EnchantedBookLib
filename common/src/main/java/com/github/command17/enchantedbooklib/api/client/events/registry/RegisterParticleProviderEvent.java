package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.client.registry.ParticleProviderRegistry;
import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterParticleProviderEvent extends Event {
    public<T extends ParticleOptions> void register(Supplier<? extends ParticleType<T>> particleType, ParticleProvider<T> provider) {
        ParticleProviderRegistry.register(particleType, provider);
    }

    public<T extends ParticleOptions> void register(ParticleType<T> particleType, ParticleProvider<T> provider) {
        ParticleProviderRegistry.register(() -> particleType, provider);
    }
}
