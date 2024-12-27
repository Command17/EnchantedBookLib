package com.github.command17.enchantedbooklib.api.config;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.config.annotation.Config;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import com.github.command17.enchantedbooklib.api.config.entry.ConfigEntry;
import com.github.command17.enchantedbooklib.api.util.PlatformHelper;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.io.BufferedWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ConfigData {
    private static final Jankson JANKSON = Jankson.builder().build();

    private final String name;

    private final ArrayList<Consumer<ConfigData>> saveListeners = new ArrayList<>();
    private final ArrayList<Consumer<ConfigData>> loadListeners = new ArrayList<>();

    private ResourceLocation id;

    public ConfigData() {
        Config definition = this.getClass().getAnnotation(Config.class);

        if (definition == null) {
            throw new NullPointerException("No @Config found for config '" + this.getClass() + "'!");
        }

        this.name = definition.name();
    }

    protected void setId(ResourceLocation id) {
        if (this.id != null) {
            throw new IllegalStateException("Cannot change id of config '" + this.name + "'!");
        }

        this.id = id;
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Path getConfigPath() {
        return PlatformHelper.getConfigPath().resolve(this.getName() + ".json5");
    }

    public ImmutableMap<Entry, ConfigEntry<?>> getEntriesWithDefinition() {
        ImmutableMap.Builder<Entry, ConfigEntry<?>> builder = ImmutableMap.builder();

        for (Field field: this.getClass().getDeclaredFields()) {
            if (!field.trySetAccessible()) {
                EnchantedBookLib.LOGGER.warn("Could not make field '{}' accessible in config '{}'!", field, this.name);

                continue;
            }

            try {
                Entry definition = field.getAnnotation(Entry.class);

                if (definition != null && field.get(this) instanceof ConfigEntry<?> entry) {
                    builder.put(definition, entry);
                }
            } catch (IllegalAccessException e) {
                EnchantedBookLib.LOGGER.error("Could not access field '{}' in config '{}'!", field, this.name, e);
            }
        }

        return builder.build();
    }

    public ImmutableCollection<ConfigEntry<?>> getEntries() {
        return this.getEntriesWithDefinition().values();
    }

    public void addSaveListener(Consumer<ConfigData> listener) {
        this.saveListeners.add(listener);
    }

    public void addLoadListener(Consumer<ConfigData> listener) {
        this.loadListeners.add(listener);
    }

    public void validate() throws InvalidConfigException {}

    public boolean hasSyncableEntries() {
        return this.getEntriesWithDefinition().entrySet()
                .stream()
                .anyMatch((entrySet) -> entrySet.getKey().synced());
    }

    public void writeBuf(FriendlyByteBuf buf) {
        this.getEntriesWithDefinition().entrySet()
                .stream()
                .filter((entrySet) -> entrySet.getKey().synced())
                .forEach((entrySet) -> entrySet.getValue().writeBuf(buf));
    }

    public void readBuf(FriendlyByteBuf buf) {
        this.getEntriesWithDefinition().entrySet()
                .stream()
                .filter((entrySet) -> entrySet.getKey().synced())
                .forEach((entrySet) -> {
                    Entry entry = entrySet.getKey();
                    ConfigEntry<?> configEntry = entrySet.getValue();
                    entrySet.getValue().readBuf(buf);
                    EnchantedBookLib.LOGGER.info("Set synced value for '{}', '{}' -> '{}'", entry.name(), configEntry.getValue(), configEntry.getValue());
                });
    }

    @SuppressWarnings("ExtractMethodRecommender")
    public void save() {
        for (var listener: this.saveListeners) {
            listener.accept(this);
        }

        try {
            Files.createDirectories(this.getConfigPath().getParent());
            JsonObject json = new JsonObject();

            for (var entrySet: this.getEntriesWithDefinition().entrySet()) {
                Entry definition = entrySet.getKey();
                ConfigEntry<?> entry = entrySet.getValue();

                String category = definition.category();
                JsonObject entryJson = json;

                if (!category.isBlank()) {
                    entryJson = json.getObject(category);

                    if (entryJson == null) {
                        entryJson = new JsonObject();
                        json.put(definition.category(), entryJson);
                    }
                }

                entry.write(definition, entryJson);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(this.getConfigPath());) {
                writer.write(json.toJson(true, true));
            }
        } catch (Exception e) {
            EnchantedBookLib.LOGGER.error("Error saving config '{}'!", this.name, e);
        }
    }

    public boolean load() {
        for (var listener: this.loadListeners) {
            listener.accept(this);
        }

        if (Files.exists(this.getConfigPath())) {
            try {
                JsonObject json = JANKSON.load(this.getConfigPath().toAbsolutePath().toFile());

                for (var entrySet: this.getEntriesWithDefinition().entrySet()) {
                    Entry definition = entrySet.getKey();
                    ConfigEntry<?> entry = entrySet.getValue();

                    String category = definition.category();
                    JsonObject entryJson = json;

                    if (!category.isBlank()) {
                        entryJson = json.getObject(category);

                        if (entryJson == null) {
                            entryJson = new JsonObject();
                            json.put(definition.category(), entryJson);
                        }
                    }

                    entry.read(definition, entryJson);
                }
            } catch (Exception e) {
                EnchantedBookLib.LOGGER.error("Error loading config '{}'!", this.name, e);

                return false;
            }
        }

        return true;
    }
}
