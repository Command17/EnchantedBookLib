package com.github.command17.enchantedbooklib.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CreativeModeTabRegistry {
    private CreativeModeTabRegistry() {}

    public static CreativeModeTab createTab(Component title, Supplier<ItemStack> icon) {
        return createTabBuilder().title(title).icon(icon).build();
    }

    @ExpectPlatform
    public static CreativeModeTab.Builder createTabBuilder() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void modifyTabContent(ResourceKey<CreativeModeTab> tabKey, Consumer<CreativeModeTab.Output> outputModifier) {
        throw new AssertionError();
    }
}
