package com.github.command17.enchantedbooklib.api.events.command;

import com.github.command17.enchantedbooklib.api.event.Event;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.Nullable;

public class PerformCommandEvent extends Event {
    private ParseResults<CommandSourceStack> results;

    @Nullable
    private Throwable throwable;

    public PerformCommandEvent(ParseResults<CommandSourceStack> results, @Nullable Throwable throwable) {
        this.results = results;
        this.throwable = throwable;
    }

    public void setResults(ParseResults<CommandSourceStack> results) {
        this.results = results;
    }

    public void setThrowable(@Nullable Throwable throwable) {
        this.throwable = throwable;
    }

    public ParseResults<CommandSourceStack> getResults() {
        return results;
    }

    @Nullable
    public Throwable getThrowable() {
        return throwable;
    }
}
