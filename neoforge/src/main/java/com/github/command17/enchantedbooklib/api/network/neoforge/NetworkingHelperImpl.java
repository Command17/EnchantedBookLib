package com.github.command17.enchantedbooklib.api.network.neoforge;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.network.NetworkDirection;
import com.github.command17.enchantedbooklib.api.network.NetworkingHelper;
import com.github.command17.enchantedbooklib.api.util.EnvSide;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

@EventBusSubscriber(modid = EnchantedBookLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class NetworkingHelperImpl {
    private NetworkingHelperImpl() {}

    private static final ArrayList<Pair<CustomPacketPayload.Type<? extends CustomPacketPayload>, StreamCodec<? super FriendlyByteBuf, ? extends CustomPacketPayload>>> S2C_PAYLOADS = new ArrayList<>();
    private static final ArrayList<Pair<CustomPacketPayload.Type<? extends CustomPacketPayload>, StreamCodec<? super FriendlyByteBuf, ? extends CustomPacketPayload>>> C2S_PAYLOADS = new ArrayList<>();

    private static final HashMap<ResourceLocation, NetworkingHelper.PacketHandler<?>> S2C_PACKET_HANDLERS = new HashMap<>();
    private static final HashMap<ResourceLocation, NetworkingHelper.PacketHandler<?>> C2S_PACKET_HANDLERS = new HashMap<>();

    public static<T extends CustomPacketPayload> void registerPayloadType(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        switch (networkDirection) {
            case S2C -> S2C_PAYLOADS.add(Pair.of(type, codec));
            case C2S -> C2S_PAYLOADS.add(Pair.of(type, codec));
        }
    }

    public static<T extends CustomPacketPayload> void registerHandler(NetworkDirection networkDirection, CustomPacketPayload.Type<T> type, NetworkingHelper.PacketHandler<T> handler) {
        switch (networkDirection) {
            case S2C -> S2C_PACKET_HANDLERS.put(type.id(), handler);
            case C2S -> C2S_PACKET_HANDLERS.put(type.id(), handler);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean canSendToServer(ResourceLocation id) {
        if (Minecraft.getInstance().getConnection() != null) {
            return Minecraft.getInstance().getConnection().hasChannel(id);
        } else {
            return false;
        }
    }

    public static boolean canSendToClient(ResourceLocation id, @NotNull ServerPlayer player) {
        return player.connection.hasChannel(id);
    }

    @OnlyIn(Dist.CLIENT)
    public static void sendToServer(CustomPacketPayload payload) {
        PacketDistributor.sendToServer(payload);
    }

    public static void sendToClient(CustomPacketPayload payload, @NotNull ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    public static void sendToClients(CustomPacketPayload payload) {
        PacketDistributor.sendToAllPlayers(payload);
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    private static void event(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        for (var pair: S2C_PAYLOADS) {
            CustomPacketPayload.Type<CustomPacketPayload> type = (CustomPacketPayload.Type<CustomPacketPayload>) pair.getKey();
            StreamCodec<? super FriendlyByteBuf, CustomPacketPayload> codec = (StreamCodec<? super FriendlyByteBuf, CustomPacketPayload>) pair.getValue();

            NetworkingHelper.PacketHandler<CustomPacketPayload> handler = (NetworkingHelper.PacketHandler<CustomPacketPayload>) S2C_PACKET_HANDLERS.get(type.id());

            IPayloadHandler<CustomPacketPayload> payloadHandler = (packet, context) -> {};

            if (handler != null) {
                payloadHandler = makePayloadHandler(handler);
            }

            registrar.playToClient(type, codec, payloadHandler);
        }

        for (var pair: C2S_PAYLOADS) {
            CustomPacketPayload.Type<CustomPacketPayload> type = (CustomPacketPayload.Type<CustomPacketPayload>) pair.getKey();
            StreamCodec<? super FriendlyByteBuf, CustomPacketPayload> codec = (StreamCodec<? super FriendlyByteBuf, CustomPacketPayload>) pair.getValue();

            NetworkingHelper.PacketHandler<CustomPacketPayload> handler = (NetworkingHelper.PacketHandler<CustomPacketPayload>) C2S_PACKET_HANDLERS.get(type.id());

            IPayloadHandler<CustomPacketPayload> payloadHandler = (packet, context) -> {};

            if (handler != null) {
                payloadHandler = makePayloadHandler(handler);
            }

            registrar.playToServer(type, codec, payloadHandler);
        }
    }

    private static<T extends CustomPacketPayload> IPayloadHandler<T> makePayloadHandler(NetworkingHelper.PacketHandler<T> handler) {
        return (packet, context) -> handler.handle(packet, new NetworkingHelper.PacketContext() {
            @Override
            public Player getPlayer() {
                return context.player();
            }

            @Override
            public void queue(Runnable runnable) {
                context.enqueueWork(runnable);
            }

            @Override
            public EnvSide getEnvSide() {
                return EnvSide.CLIENT;
            }});
    }
}
