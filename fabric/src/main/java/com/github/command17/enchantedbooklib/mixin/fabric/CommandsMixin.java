package com.github.command17.enchantedbooklib.mixin.fabric;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.command.PerformCommandEvent;
import com.google.common.base.Throwables;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ContextChain;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Commands.class)
public class CommandsMixin {
    @ModifyVariable(method = "finishParsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/Commands;validateParseResults(Lcom/mojang/brigadier/ParseResults;)V"), argsOnly = true)
    private static ParseResults<CommandSourceStack> enchantedbooklib$finishParsing(ParseResults<CommandSourceStack> results) {
        PerformCommandEvent performCommandEvent = new PerformCommandEvent(results, null);
        EventManager.invoke(performCommandEvent);

        if (performCommandEvent.getThrowable() != null) {
            Throwables.throwIfUnchecked(performCommandEvent.getThrowable());
            return null;
        }

        return performCommandEvent.getResults();
    }

    @Inject(method = "finishParsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/Commands;validateParseResults(Lcom/mojang/brigadier/ParseResults;)V"), cancellable = true)
    private static void enchantedbooklib$finishParsing(ParseResults<CommandSourceStack> results, String command, CommandSourceStack stack, CallbackInfoReturnable<ContextChain<CommandSourceStack>> cir) {
        if (results == null) cir.setReturnValue(null);
    }
}
