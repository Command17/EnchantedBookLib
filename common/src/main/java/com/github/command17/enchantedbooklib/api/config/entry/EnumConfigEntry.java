package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public class EnumConfigEntry<T extends Enum<T>> extends ConfigEntry<Enum<T>> {
    public EnumConfigEntry(@NotNull Enum<T> value) {
        super(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(Entry definition, JsonObject json) {
        Enum<T> data = (Enum<T>) json.get(this.getDefaultValue().getClass(), definition.name());

        if (data != null) {
            this.setValue(data);
        } else {
            this.setValue(this.getDefaultValue());
        }
    }

    @Override
    public void write(Entry definition, JsonObject json) {
        json.putDefault(definition.name(), this.getValue(), definition.comment());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readBuf(FriendlyByteBuf buf) {
        this.setSyncedValue(buf.readEnum(this.getDefaultValue().getClass()));
    }

    @Override
    public void writeBuf(FriendlyByteBuf buf) {
        buf.writeEnum(this.getValue());
    }
}
