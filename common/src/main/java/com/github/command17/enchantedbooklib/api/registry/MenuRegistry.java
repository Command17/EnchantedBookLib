package com.github.command17.enchantedbooklib.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class MenuRegistry {
    private MenuRegistry() {}

    public static void openExtraDataMenu(ServerPlayer player, MenuProvider menuProvider, Consumer<FriendlyByteBuf> extraDataWriter) {
        openExtraDataMenu(player, new IExtraDataMenuProvider() {
            @Override
            public void writeExtraData(FriendlyByteBuf buf) {
                extraDataWriter.accept(buf);
            }

            @NotNull
            @Override
            public Component getDisplayName() {
                return menuProvider.getDisplayName();
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return menuProvider.createMenu(i, inventory, player);
            }
        });
    }

    @ExpectPlatform
    public static void openExtraDataMenu(ServerPlayer player, IExtraDataMenuProvider menuProvider) {
        throw new AssertionError();
    }

    public static void openMenu(ServerPlayer player, MenuProvider menuProvider) {
        player.openMenu(menuProvider);
    }

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuTypeFactory<T> factory) {
        throw new AssertionError();
    }

    @Environment(EnvType.CLIENT)
    @ExpectPlatform
    public static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> void registerScreenFactory(MenuType<? extends C> menuType, ScreenFactory<C, S> factory) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface ScreenFactory<C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> {
        S create(C container, Inventory inventory, Component title);
    }

    @FunctionalInterface
    public interface MenuTypeFactory<T extends AbstractContainerMenu> {
        T create(int id, Inventory inventory, FriendlyByteBuf buf);
    }
}
