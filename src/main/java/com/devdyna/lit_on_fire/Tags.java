package com.devdyna.lit_on_fire;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static final TagKey<Item> CAN_LIT_BLOCKS = TagKey.create(BuiltInRegistries.ITEM.key(),
            Identifier.fromNamespaceAndPath(Main.MODID, "can_lit_blocks"));

    public static final TagKey<Block> CAN_BE_LIT_UP = TagKey.create(BuiltInRegistries.BLOCK.key(),
            Identifier.fromNamespaceAndPath(Main.MODID, "can_be_lit_up"));
}
