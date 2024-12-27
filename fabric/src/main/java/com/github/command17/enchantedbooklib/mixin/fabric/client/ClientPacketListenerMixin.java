package com.github.command17.enchantedbooklib.mixin.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.entity.ClientPlayerEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin extends ClientCommonPacketListenerImpl {
    @Unique
    private LocalPlayer oldPlayer;

    protected ClientPacketListenerMixin(Minecraft minecraft, Connection connection, CommonListenerCookie commonListenerCookie) {
        super(minecraft, connection, commonListenerCookie);
    }

    @Inject(method = "handleLogin", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;setServerRenderDistance(I)V"))
    private void enchantedbooklib$onJoin(ClientboundLoginPacket packet, CallbackInfo ci) {
        EventManager.invoke(new ClientPlayerEvent.Join(this.minecraft.player));
    }

    @Inject(method = "handleRespawn", at = @At("HEAD"))
    private void enchantedbooklib$beforeRespawn(ClientboundRespawnPacket packet, CallbackInfo ci) {
        this.oldPlayer = this.minecraft.player;
    }

    @Inject(method = "handleRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addEntity(Lnet/minecraft/world/entity/Entity;)V"))
    private void enchantedbooklib$onRespawn(ClientboundRespawnPacket packet, CallbackInfo ci) {
        EventManager.invoke(new ClientPlayerEvent.Respawn(this.oldPlayer, this.minecraft.player));
        this.oldPlayer = null;
    }
}
