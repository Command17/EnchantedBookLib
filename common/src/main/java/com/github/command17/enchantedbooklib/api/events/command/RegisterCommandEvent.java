package com.github.command17.enchantedbooklib.api.events.command;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RegisterCommandEvent extends Event {
    private final CommandDispatcher<CommandSourceStack> dispatcher;
    private final CommandBuildContext registry;
    private final Commands.CommandSelection selection;

    public RegisterCommandEvent(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection) {
        this.dispatcher = dispatcher;
        this.registry = registry;
        this.selection = selection;
    }

    public CommandDispatcher<CommandSourceStack> getDispatcher() {
        return dispatcher;
    }

    public CommandBuildContext getRegistry() {
        return registry;
    }

    public Commands.CommandSelection getSelection() {
        return selection;
    }
}
