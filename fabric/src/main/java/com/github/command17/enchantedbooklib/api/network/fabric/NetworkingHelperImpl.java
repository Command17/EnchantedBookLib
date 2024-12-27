package com.github.command17.enchantedbooklib.api.network.fabric;

import com.github.command17.enchantedbooklib.api.hooks.MinecraftServerHooks;
import com.github.command17.enchantedbooklib.api.network.NetworkDirection;
import com.github.command17.enchantedbooklib.api.network.NetworkingHelper;
import com.github.command17.enchantedbooklib.api.util.EnvSide;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public final class NetworkingHelperImpl {
    private NetworkingHelperImpl() {}

    public static<T extends CustomPacketPayload> void registerPayloadType(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        switch (networkDirection) {
            case C2S -> PayloadTypeRegistry.playC2S().register(type, codec);
            case S2C -> PayloadTypeRegistry.playS2C().register(type, codec);
        }
    }

    public static<T extends CustomPacketPayload> void registerHandler(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, NetworkingHelper.PacketHandler<T> handler) {
        switch (networkDirection) {
            case C2S -> ServerPlayNetworking.registerGlobalReceiver(type,
                    (packet, context) -> handler.handle(packet, new NetworkingHelper.PacketContext() {
                        @Override
                        public Player getPlayer() {
                            return context.player();
                        }

                        @Override
                        public void queue(Runnable runnable) {
                            context.server().execute(runnable);
                        }

                        @Override
                        public EnvSide getEnvSide() {
                            return EnvSide.SERVER;
                        }
                    }));

            case S2C -> ClientPlayNetworking.registerGlobalReceiver(type,
                    (packet, context) -> handler.handle(packet, new NetworkingHelper.PacketContext() {
                        @Override
                        public Player getPlayer() {
                            return context.player();
                        }

                        @Override
                        public void queue(Runnable runnable) {
                            context.client().execute(runnable);
                        }

                        @Override
                        public EnvSide getEnvSide() {
                            return EnvSide.CLIENT;
                        }
                    }));
        }
    }

    @Environment(EnvType.CLIENT)
    public static boolean canSendToServer(ResourceLocation id) {
        return ClientPlayNetworking.canSend(id);
    }

    public static boolean canSendToClient(ResourceLocation id, @NotNull ServerPlayer player) {
        return ServerPlayNetworking.canSend(player, id);
    }

    @Environment(EnvType.CLIENT)
    public static void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }

    public static void sendToClient(CustomPacketPayload payload, @NotNull ServerPlayer player) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void sendToClients(CustomPacketPayload payload) {
        MinecraftServerHooks.getServer().ifPresent(
                (server) -> PlayerLookup.all(server).forEach(
                        (player) -> ServerPlayNetworking.send(player, payload)));
    }
}
