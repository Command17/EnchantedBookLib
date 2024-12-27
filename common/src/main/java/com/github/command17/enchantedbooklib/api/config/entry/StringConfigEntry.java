package com.github.command17.enchantedbooklib.api.config.entry;

import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public class StringConfigEntry extends ConfigEntry<String> {
    public StringConfigEntry(@NotNull String value) {
        super(value);
    }

    @Override
    public void read(Entry definition, JsonObject json) {
        String data = json.get(String.class, definition.name());

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

    @Override
    public void readBuf(FriendlyByteBuf buf) {
        this.setSyncedValue(buf.readUtf());
    }

    @Override
    public void writeBuf(FriendlyByteBuf buf) {
        buf.writeUtf(this.getValue());
    }
}
