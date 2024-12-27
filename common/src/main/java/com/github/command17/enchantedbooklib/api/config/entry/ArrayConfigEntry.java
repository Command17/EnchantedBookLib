package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@ApiStatus.Experimental
public class ArrayConfigEntry<T> extends ConfigEntry<T[]> {
    public ArrayConfigEntry(T @NotNull [] value) {
        super(value);
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        T[] data = (T[]) json.get(this.getDefaultValue().getClass(), definition.name());

        this.setValue(Objects.requireNonNullElseGet(data, this::getDefaultValue));
    }

    @Override
    public void write(Entry definition, JsonObject json) {
        json.putDefault(definition.name(), this.getValue(), definition.comment());
    }

    // TODO: Figure this out
    @Override
    public void readBuf(FriendlyByteBuf buf) {
        throw new RuntimeException("Cannot sync ArrayConfigEntry!");
    }

    @Override
    public void writeBuf(FriendlyByteBuf buf) {
        throw new RuntimeException("Cannot sync ArrayConfigEntry!");
    }
}
