package com.github.command17.enchantedbooklib.core;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.config.ConfigData;
import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SyncConfigPacket(ConfigData config) implements CustomPacketPayload {
    public static final Type<SyncConfigPacket> TYPE = new Type<>(EnchantedBookLib.resource("sync_config"));

    public static final StreamCodec<FriendlyByteBuf, SyncConfigPacket> CODEC = StreamCodec.of(
            SyncConfigPacket::writeSyncConfigPacket,
            SyncConfigPacket::handleSyncConfigPacket
    );

    public static SyncConfigPacket handleSyncConfigPacket(FriendlyByteBuf buf) {
        ResourceLocation id = ResourceLocation.STREAM_CODEC.decode(buf);
        ConfigData config = LibConfigs.getConfig(id);
        EnchantedBookLib.LOGGER.info("Loading config '{}' from server...", id);

        if (config != null) {
            config.readBuf(buf);
        } else {
            EnchantedBookLib.LOGGER.error("Trying to synchronise unknown config '{}'!", id);
        }

        return new SyncConfigPacket(config);
    }

    public static void writeSyncConfigPacket(FriendlyByteBuf buf, SyncConfigPacket packet) {
        EnchantedBookLib.LOGGER.info("Sending config '{}' to client...", packet.config.getId());
        ResourceLocation.STREAM_CODEC.encode(buf, packet.config.getId());
        packet.config.writeBuf(buf);
    }

    @NotNull
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
