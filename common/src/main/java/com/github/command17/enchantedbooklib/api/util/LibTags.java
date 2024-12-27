package com.github.command17.enchantedbooklib.api.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class LibTags {
    private LibTags() {}

    public final static class ItemTags {
        private ItemTags() {}

        // Storage Blocks
        public static final TagKey<Item> STORAGE_BLOCKS = conventionTag("storage_blocks");
        public static final TagKey<Item> NETHERITE_STORAGE_BLOCK = conventionTag("storage_blocks/netherite");
        public static final TagKey<Item> DIAMOND_STORAGE_BLOCK = conventionTag("storage_blocks/diamond");
        public static final TagKey<Item> EMERALD_STORAGE_BLOCK = conventionTag("storage_blocks/emerald");
        public static final TagKey<Item> GOLD_STORAGE_BLOCK = conventionTag("storage_blocks/gold");
        public static final TagKey<Item> IRON_STORAGE_BLOCK = conventionTag("storage_blocks/iron");
        public static final TagKey<Item> COPPER_STORAGE_BLOCK = conventionTag("storage_blocks/copper");
        public static final TagKey<Item> LAPIS_STORAGE_BLOCK = conventionTag("storage_blocks/lapis");
        public static final TagKey<Item> REDSTONE_STORAGE_BLOCK = conventionTag("storage_blocks/redstone");
        public static final TagKey<Item> RAW_IRON_STORAGE_BLOCK = conventionTag("storage_blocks/raw_iron");
        public static final TagKey<Item> RAW_COPPER_STORAGE_BLOCK = conventionTag("storage_blocks/raw_copper");
        public static final TagKey<Item> RAW_GOLD_STORAGE_BLOCK = conventionTag("storage_blocks/raw_gold");
        public static final TagKey<Item> WHEAT_STORAGE_BLOCK = conventionTag("storage_blocks/wheat");

        // Raw Blocks
        public static final TagKey<Item> RAW_BLOCKS = conventionTag("raw_blocks");
        public static final TagKey<Item> RAW_IRON = conventionTag("raw_blocks/iron");
        public static final TagKey<Item> RAW_COPPER = conventionTag("raw_blocks/copper");
        public static final TagKey<Item> RAW_GOLD = conventionTag("raw_blocks/gold");

        // Stones
        public static final TagKey<Item> STONES = conventionTag("stones");
        public static final TagKey<Item> COBBLESTONES = conventionTag("cobblestones");
        public static final TagKey<Item> SANDSTONES = conventionTag("sandstones");

        // Ores
        public static final TagKey<Item> ORES = conventionTag("ores");
        public static final TagKey<Item> COALS = conventionTag("coals");

        // Ingots
        public static final TagKey<Item> INGOTS = conventionTag("ingots");
        public static final TagKey<Item> NUGGETS = conventionTag("nuggets");
        public static final TagKey<Item> GEMS = conventionTag("gems");

        // Dusts
        public static final TagKey<Item> DUSTS = conventionTag("dusts");

        // Rods
        public static final TagKey<Item> RODS = conventionTag("rods");

        // Tools
        public static final TagKey<Item> TOOLS = conventionTag("tools");
        public static final TagKey<Item> MINING_TOOLS = conventionTag("tools/mining_tools");
        public static final TagKey<Item> RANGED_WEAPONS = conventionTag("tools/ranged_weapons");
        public static final TagKey<Item> MELEE_WEAPONS = conventionTag("tools/melee_weapons");
        public static final TagKey<Item> SPEARS = conventionTag("tools/spears");
        public static final TagKey<Item> BOWS = conventionTag("tools/bows");
        public static final TagKey<Item> CROSSBOWS = conventionTag("tools/crossbows");
        public static final TagKey<Item> SHIELDS = conventionTag("tools/shields");
        public static final TagKey<Item> FISHING_RODS = conventionTag("tools/fishing_rods");
        public static final TagKey<Item> BRUSHES = conventionTag("tools/brushes");
        public static final TagKey<Item> SHEARS = conventionTag("tools/shears");

        // Potions
        public static final TagKey<Item> POTIONS = conventionTag("potions");

        // Foods
        public static final TagKey<Item> FOODS = conventionTag("foods");
        public static final TagKey<Item> ANIMAL_FOODS = conventionTag("animal_foods");

        // Workstations
        public static final TagKey<Item> VILLAGER_JOB_SITES = conventionTag("villager_job_sites");

        // Other
        public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = conventionTag("hidden_from_recipe_viewers");

        private static TagKey<Item> conventionTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public final static class BlockTags {
        private BlockTags() {}

        // Storage Blocks
        public static final TagKey<Block> STORAGE_BLOCKS = conventionTag("storage_blocks");
        public static final TagKey<Block> NETHERITE_STORAGE_BLOCK = conventionTag("storage_blocks/netherite");
        public static final TagKey<Block> DIAMOND_STORAGE_BLOCK = conventionTag("storage_blocks/diamond");
        public static final TagKey<Block> EMERALD_STORAGE_BLOCK = conventionTag("storage_blocks/emerald");
        public static final TagKey<Block> GOLD_STORAGE_BLOCK = conventionTag("storage_blocks/gold");
        public static final TagKey<Block> IRON_STORAGE_BLOCK = conventionTag("storage_blocks/iron");
        public static final TagKey<Block> COPPER_STORAGE_BLOCK = conventionTag("storage_blocks/copper");
        public static final TagKey<Block> LAPIS_STORAGE_BLOCK = conventionTag("storage_blocks/lapis");
        public static final TagKey<Block> REDSTONE_STORAGE_BLOCK = conventionTag("storage_blocks/redstone");
        public static final TagKey<Block> RAW_IRON_STORAGE_BLOCK = conventionTag("storage_blocks/raw_iron");
        public static final TagKey<Block> RAW_COPPER_STORAGE_BLOCK = conventionTag("storage_blocks/raw_copper");
        public static final TagKey<Block> RAW_GOLD_STORAGE_BLOCK = conventionTag("storage_blocks/raw_gold");
        public static final TagKey<Block> WHEAT_STORAGE_BLOCK = conventionTag("storage_blocks/wheat");

        // Raw Blocks
        public static final TagKey<Block> RAW_BLOCKS = conventionTag("raw_blocks");
        public static final TagKey<Block> RAW_IRON = conventionTag("raw_blocks/iron");
        public static final TagKey<Block> RAW_COPPER = conventionTag("raw_blocks/copper");
        public static final TagKey<Block> RAW_GOLD = conventionTag("raw_blocks/gold");

        // Stones
        public static final TagKey<Block> STONES = conventionTag("stones");
        public static final TagKey<Block> COBBLESTONES = conventionTag("cobblestones");
        public static final TagKey<Block> SANDSTONES = conventionTag("sandstones");

        // Ores
        public static final TagKey<Block> ORES = conventionTag("ores");
        public static final TagKey<Block> COALS = conventionTag("coals");

        // Workstations
        public static final TagKey<Block> VILLAGER_JOB_SITES = conventionTag("villager_job_sites");

        // Other
        public static final TagKey<Block> HIDDEN_FROM_RECIPE_VIEWERS = conventionTag("hidden_from_recipe_viewers");

        private static TagKey<Block> conventionTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public final static class FluidTags {
        private FluidTags() {}

        // Basic
        public static final TagKey<Fluid> GASEOUS = conventionTag("gaseous");
        public static final TagKey<Fluid> POTION = conventionTag("potion");

        // Other
        public static final TagKey<Fluid> HIDDEN_FROM_RECIPE_VIEWERS = conventionTag("hidden_from_recipe_viewers");

        private static TagKey<Fluid> conventionTag(String name) {
            return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public final static class EntityTags {
        private EntityTags() {}

        // Basic
        public static final TagKey<EntityType<?>> BOATS = conventionTag("boats");
        public static final TagKey<EntityType<?>> BOSSES = conventionTag("bosses");
        public static final TagKey<EntityType<?>> MINECARTS = conventionTag("minecarts");

        private static TagKey<EntityType<?>> conventionTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}

