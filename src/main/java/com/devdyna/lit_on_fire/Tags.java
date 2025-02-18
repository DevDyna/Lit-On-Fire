package com.devdyna.lit_on_fire;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class Tags {
    public static final TagKey<Item> ITEM_TORCHES = TagKey.create(BuiltInRegistries.ITEM.key(),
            ResourceLocation.fromNamespaceAndPath(Main.MODID, "can_lit_campfire"));
}
