package com.github.command17.enchantedbooklib.api.client.events.registry;

import com.github.command17.enchantedbooklib.api.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class RegisterParticleProviderEvent extends Event {
    private final BiConsumer<ParticleType<?>, ParticleProvider<?>> registrar;

    public RegisterParticleProviderEvent(BiConsumer<ParticleType<?>, ParticleProvider<?>> registrar) {
        this.registrar = registrar;
    }

    public<T extends ParticleOptions> void register(Supplier<? extends ParticleType<T>> particleType, ParticleProvider<T> provider) {
        registrar.accept(particleType.get(), provider);
    }

    public<T extends ParticleOptions> void register(ParticleType<T> particleType, ParticleProvider<T> provider) {
        registrar.accept(particleType, provider);
    }
}
