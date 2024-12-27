package com.github.command17.enchantedbooklib.mixin.fabric.client;

import com.github.command17.enchantedbooklib.api.client.events.input.RawKeyPressEvent;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("RETURN"))
    private void enchantedbooklib$onRawKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (windowPointer == this.minecraft.getWindow().getWindow()) {
            EventManager.invoke(new RawKeyPressEvent(this.minecraft, key, scanCode, action, modifiers));
        }
    }
}
