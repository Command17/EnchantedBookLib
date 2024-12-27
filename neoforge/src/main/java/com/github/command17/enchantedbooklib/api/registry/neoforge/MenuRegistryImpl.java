package com.github.command17.enchantedbooklib.api.registry.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.hooks.neoforge.EventBusHooks;
import com.github.command17.enchantedbooklib.api.registry.IExtraDataMenuProvider;
import com.github.command17.enchantedbooklib.api.registry.MenuRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

public final class MenuRegistryImpl {
    private MenuRegistryImpl() {}

    public static void openExtraDataMenu(ServerPlayer player, IExtraDataMenuProvider menuProvider) {
        player.openMenu(menuProvider, menuProvider::writeExtraData);
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuRegistry.MenuTypeFactory<T> factory) {
        return IMenuTypeExtension.create(factory::create);
    }

    @OnlyIn(Dist.CLIENT)
    public static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> void registerScreenFactory(MenuType<? extends C> menuType, MenuRegistry.ScreenFactory<C, S> factory) {
        EventBusHooks.getModEventBus(EnchantedBookLib.MOD_ID).ifPresent(
                (eventBus) -> eventBus.addListener(RegisterMenuScreensEvent.class, event -> event.register(menuType, factory::create)));
    }
}
