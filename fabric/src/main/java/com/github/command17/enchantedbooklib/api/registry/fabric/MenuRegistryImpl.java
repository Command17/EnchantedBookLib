package com.github.command17.enchantedbooklib.api.registry.fabric;

import com.github.command17.enchantedbooklib.api.registry.IExtraDataMenuProvider;
import com.github.command17.enchantedbooklib.api.registry.MenuRegistry;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public final class MenuRegistryImpl {
    private MenuRegistryImpl() {}

    public static void openExtraDataMenu(ServerPlayer player, IExtraDataMenuProvider menuProvider) {
        player.openMenu(new ExtendedScreenHandlerFactory<byte[]>() {
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return menuProvider.createMenu(i, inventory, player);
            }

            @NotNull
            @Override
            public Component getDisplayName() {
                return menuProvider.getDisplayName();
            }

            @Override
            public byte[] getScreenOpeningData(ServerPlayer serverPlayer) {
                FriendlyByteBuf buf = PacketByteBufs.create();
                menuProvider.writeExtraData(buf);
                byte[] bytes = ByteBufUtil.getBytes(buf);
                buf.release();
                return bytes;
            }
        });
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuRegistry.MenuTypeFactory<T> factory) {
        return new ExtendedScreenHandlerType<>((syncId, inventory, data) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(data));
            T menu = factory.create(syncId, inventory, buf);
            buf.release();
            return menu;
        }, ByteBufCodecs.BYTE_ARRAY.mapStream(Function.identity()));
    }

    @Environment(EnvType.CLIENT)
    public static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> void registerScreenFactory(MenuType<? extends C> menuType, MenuRegistry.ScreenFactory<C, S> factory) {
        MenuScreens.register(menuType, factory::create);
    }
}
