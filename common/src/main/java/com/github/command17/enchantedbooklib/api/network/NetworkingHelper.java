package com.github.command17.enchantedbooklib.api.network;

import com.github.command17.enchantedbooklib.api.util.EnvSide;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public final class NetworkingHelper {
    private NetworkingHelper() {}

    @ExpectPlatform
    public static<T extends CustomPacketPayload> void registerPayloadType(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        throw new AssertionError();
    }

    public static<T extends CustomPacketPayload> void registerS2CPayloadType(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        registerPayloadType(NetworkDirection.S2C, type, codec);
    }

    public static<T extends CustomPacketPayload> void registerC2SPayloadType(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        registerPayloadType(NetworkDirection.C2S, type, codec);
    }

    @ExpectPlatform
    public static<T extends CustomPacketPayload> void registerHandler(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, PacketHandler<T> handler) {
        throw new AssertionError();
    }

    public static<T extends CustomPacketPayload> void registerS2CHandler(CustomPacketPayload.Type<T> type, PacketHandler<T> handler) {
        registerHandler(NetworkDirection.S2C, type, handler);
    }

    public static<T extends CustomPacketPayload> void registerC2SHandler(CustomPacketPayload.Type<T> type, PacketHandler<T> handler) {
        registerHandler(NetworkDirection.C2S, type, handler);
    }

    @Environment(EnvType.CLIENT)
    @ExpectPlatform
    public static boolean canSendToServer(ResourceLocation id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean canSendToClient(ResourceLocation id, @NotNull ServerPlayer player) {
        throw new AssertionError();
    }

    @Environment(EnvType.CLIENT)
    public static boolean canSendToServer(CustomPacketPayload.Type<?> type) {
        return canSendToServer(type.id());
    }

    public static boolean canSendToClient(CustomPacketPayload.Type<?> type, @NotNull ServerPlayer player) {
        return canSendToClient(type.id(), player);
    }

    @Environment(EnvType.CLIENT)
    @ExpectPlatform
    public static void sendToServer(CustomPacketPayload payload) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToClient(CustomPacketPayload payload, @NotNull ServerPlayer player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToClients(CustomPacketPayload payload) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface PacketHandler<T> {
        void handle(T value, PacketContext context);
    }

    public interface PacketContext {
        Player getPlayer();

        void queue(Runnable runnable);

        EnvSide getEnvSide();
    }
}
