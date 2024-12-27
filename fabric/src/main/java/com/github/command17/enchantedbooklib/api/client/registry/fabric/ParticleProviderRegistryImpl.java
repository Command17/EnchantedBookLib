package com.github.command17.enchantedbooklib.api.client.registry.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ParticleProviderRegistryImpl {
    private ParticleProviderRegistryImpl() {}

    public static <T extends ParticleOptions> void register(Supplier<? extends ParticleType<T>> particleType, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(particleType.get(), provider);
    }
}
