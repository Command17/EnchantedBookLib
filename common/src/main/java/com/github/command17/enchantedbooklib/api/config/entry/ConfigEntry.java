package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class ConfigEntry<T> implements Supplier<T> {
    @NotNull
    private final T defaultValue;

    @NotNull
    private T value;

    @Nullable
    private T syncedValue;

    public ConfigEntry(@NotNull T value) {
        this.defaultValue = value;
        this.value = value;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    public void setSyncedValue(@Nullable T syncedValue) {
        this.syncedValue = syncedValue;
    }

    public boolean hasSyncedValue() {
        return this.syncedValue != null;
    }

    @NotNull
    public T getDefaultValue() {
        return defaultValue;
    }

    @NotNull
    public T getValue() {
        return this.get();
    }

    @NotNull
    @Override
    public T get() {
        if (this.hasSyncedValue()) {
            return this.syncedValue;
        }

        return this.value;
    }

    public abstract void read(Entry definition, JsonObject json);
    public abstract void write(Entry definition, JsonObject json);

    public abstract void readBuf(FriendlyByteBuf buf);
    public abstract void writeBuf(FriendlyByteBuf buf);
}
