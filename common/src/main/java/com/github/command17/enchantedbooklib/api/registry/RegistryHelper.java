package com.github.command17.enchantedbooklib.api.registry;

import com.github.command17.enchantedbooklib.EnchantedBookLib;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.events.registry.RegistryEvent;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryHelper<T> implements Iterable<IRegistrySupplier<T>> {
    private final String modId;
    private final ResourceKey<? extends Registry<T>> registryKey;
    private final Registry<T> registry;

    private final ArrayList<IRegistrySupplier<T>> entries = new ArrayList<>();

    private boolean frozen = false;

    protected RegistryHelper(String modId, ResourceKey<? extends Registry<T>> registryKey, Registry<T> registry) {
        this.modId = modId;
        this.registryKey = registryKey;
        this.registry = registry;
    }

    @SuppressWarnings("unchecked")
    public static<T> RegistryHelper<T> create(String modId, ResourceKey<? extends Registry<T>> registryKey) {
        return new RegistryHelper<>(modId, registryKey, (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location()));
    }

    public static<T> RegistryHelper<T> create(String modId, Registry<T> registry) {
        return new RegistryHelper<>(modId, registry.key(), registry);
    }

    public static ItemHelper createItemHelper(String modId, ResourceKey<CreativeModeTab> tab) {
        return new ItemHelper(modId, tab);
    }

    public static ItemHelper createItemHelper(String modId) {
        return new ItemHelper(modId, null);
    }

    public static BlockHelper createBlockHelper(String modId) {
        return new BlockHelper(modId);
    }

    public ResourceLocation makeId(String name) {
        return ResourceLocation.fromNamespaceAndPath(this.modId, name);
    }

    public ResourceKey<T> makeKey(String name) {
        return ResourceKey.create(this.registryKey, this.makeId(name));
    }

    public<E extends T> IRegistrySupplier<E> register(String name, Supplier<? extends E> entry) {
        return this.register(this.makeId(name), entry);
    }

    @SuppressWarnings("unchecked")
    public<E extends T> IRegistrySupplier<E> register(ResourceLocation id, Supplier<? extends E> entry) {
        if (this.frozen) {
            EnchantedBookLib.LOGGER.error("RegistryHelper is frozen!");
            return null;
        }

        ResourceKey<T> key = ResourceKey.create(this.registryKey, id);
        Supplier<E> memoizedEntry = Suppliers.memoize(entry::get);
        Supplier<Holder<T>> memoizedHolderGetter = Suppliers.memoize(() -> this.getRegistry().getHolder(id).orElse(null));
        IRegistrySupplier<T> registrySupplier = new IRegistrySupplier<>() {
            @Override
            public ResourceLocation getId() {
                return id;
            }

            @Override
            public ResourceKey<T> getKey() {
                return key;
            }

            @Override
            public Holder<T> getHolder() {
                return memoizedHolderGetter.get();
            }

            @Override
            public T get() {
                return memoizedEntry.get();
            }
        };

        this.entries.add(registrySupplier);
        return (IRegistrySupplier<E>) registrySupplier;
    }

    public void register() {
        if (this.frozen) {
            EnchantedBookLib.LOGGER.error("RegistryHelper cannot be registered twice!");
            return;
        }

        this.frozen = true;
        EventManager.registerListener(this::event);
    }

    private void event(RegistryEvent event) {
        for (IRegistrySupplier<T> entry: this.entries) {
            event.register(this.registryKey, entry.getId(), entry);
        }
    }

    public Optional<IRegistrySupplier<T>> getOptionalEntry(ResourceLocation id) {
        return this.entries.stream().filter((entry) -> entry.getId() == id).findAny();
    }

    public IRegistrySupplier<T> getEntry(ResourceLocation id) {
        return this.getOptionalEntry(id).orElse(null);
    }

    public String getModId() {
        return this.modId;
    }

    public Registry<T> getRegistry() {
        return this.registry;
    }

    public ResourceKey<? extends Registry<T>> getRegistryKey() {
        return this.registryKey;
    }

    public ImmutableList<IRegistrySupplier<T>> getEntries() {
        return ImmutableList.copyOf(this.entries);
    }

    public boolean isFrozen() {
        return frozen;
    }

    @NotNull
    @Override
    public Iterator<IRegistrySupplier<T>> iterator() {
        return this.entries.iterator();
    }

    public static class ItemHelper extends RegistryHelper<Item> {
        @Nullable
        private final ResourceKey<CreativeModeTab> tab;

        protected ItemHelper(String modId, @Nullable ResourceKey<CreativeModeTab> tab) {
            super(modId, Registries.ITEM, BuiltInRegistries.ITEM);
            this.tab = tab;
        }

        public IRegistrySupplier<Item> registerSimpleItem(String name) {
            return this.register(name, () -> new Item(new Item.Properties()));
        }

        public IRegistrySupplier<Item> registerSimpleBlockItem(String name, Supplier<Block> block) {
            return this.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public<T extends Item> IRegistrySupplier<T> registerItem(String name, Function<Item.Properties, T> itemFactory) {
            return this.register(name, () -> itemFactory.apply(new Item.Properties()));
        }

        public<T extends Item> IRegistrySupplier<T> registerItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties properties) {
            return this.register(name, () -> itemFactory.apply(properties));
        }

        @Override
        public void register() {
            super.register();

            if (this.tab != null) {
                CreativeModeTabRegistry.modifyTabContent(this.tab,
                        (output) -> this.getEntries().forEach((entry) -> output.accept(entry.get())));
            }
        }
    }

    public static class BlockHelper extends RegistryHelper<Block> {
        protected BlockHelper(String modId) {
            super(modId, Registries.BLOCK, BuiltInRegistries.BLOCK);
        }

        public IRegistrySupplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, Supplier<Block> copyBlock) {
            return this.register(name, () -> blockFactory.apply(BlockBehaviour.Properties.ofFullCopy(copyBlock.get())));
        }

        public IRegistrySupplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
            return this.register(name, () -> blockFactory.apply(properties));
        }
    }
}
