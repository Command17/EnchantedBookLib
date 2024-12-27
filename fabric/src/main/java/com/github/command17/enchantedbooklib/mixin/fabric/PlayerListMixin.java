package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.entity.PlayerEvent;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At("RETURN"))
    private void enchantedbooklib$onJoin(Connection connection, ServerPlayer player, CommonListenerCookie cookie, CallbackInfo ci) {
        EventManager.invoke(new PlayerEvent.Join(player));
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void enchantedbooklib$onQuit(ServerPlayer player, CallbackInfo ci) {
        EventManager.invoke(new PlayerEvent.Quit(player));
    }

    @Inject(method = "respawn", at = @At("RETURN"))
    private void enchantedbooklib$onRespawn(ServerPlayer player, boolean keepInventory, Entity.RemovalReason reason, CallbackInfoReturnable<ServerPlayer> cir) {
        EventManager.invoke(new PlayerEvent.Respawn(cir.getReturnValue(), reason));
    }
}
