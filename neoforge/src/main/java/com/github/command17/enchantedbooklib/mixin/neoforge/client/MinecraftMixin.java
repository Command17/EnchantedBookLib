package com.github.command17.enchantedbooklib.mixin.neoforge.client;

import com.github.command17.enchantedbooklib.api.client.events.ClientLifecycleEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "run", at = @At(value = "INVOKE", target = "Ljava/lang/Thread;currentThread()Ljava/lang/Thread;"))
    private void enchantedbooklib$onStart(CallbackInfo ci) {
        EventManager.invoke(new ClientLifecycleEvent.Started((Minecraft) ((Object) this)));
    }

    @Inject(method = "destroy", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;)V"))
    private void enchantedbooklib$onStop(CallbackInfo ci) {
        EventManager.invoke(new ClientLifecycleEvent.Stopped((Minecraft) ((Object) this)));
    }
}
