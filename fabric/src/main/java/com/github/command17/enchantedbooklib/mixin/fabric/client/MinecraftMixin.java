package com.github.command17.enchantedbooklib.mixin.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.entity.ClientPlayerEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GameNarrator;clear()V"))
    private void enchantedbooklib$onQuit(Screen screen, boolean retainDownloadedPacks, CallbackInfo ci) {
        EventManager.invoke(new ClientPlayerEvent.Quit(this.player));
    }
}
