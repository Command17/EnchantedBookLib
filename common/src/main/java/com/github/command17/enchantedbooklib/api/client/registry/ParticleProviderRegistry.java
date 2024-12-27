package com.github.command17.enchantedbooklib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ParticleProviderRegistry {
    private ParticleProviderRegistry() {}

    @ExpectPlatform
    public static<T extends ParticleOptions> void register(Supplier<? extends ParticleType<T>> particleType, ParticleProvider<T> provider) {
        throw new AssertionError();
    }
}
