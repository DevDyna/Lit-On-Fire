package com.devdyna.lit_on_fire;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

@Mod(LitOnFire.MODID)
public class LitOnFire {

    public static final String MODID = "lit_on_fire";

    public static final TagKey<Item> TORCHES = TagKey.create(BuiltInRegistries.ITEM.key(),
            ResourceLocation.fromNamespaceAndPath(MODID, "can_lit_campfire"));

    public LitOnFire(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void TorchclickOnCampfire(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().getBlockState(event.getPos()).is(BlockTags.CAMPFIRES)
                &&
                getState(event).getValue(BlockStateProperties.LIT)
                && event.getItemStack().is(TORCHES)) {
            event.getEntity().swing(InteractionHand.MAIN_HAND);
            event.getLevel().setBlockAndUpdate(event.getPos(),
                    getState(event).setValue(BlockStateProperties.LIT, true));

        }
    }

    public BlockState getState(RightClickBlock event) {
        return event.getLevel().getBlockState(event.getPos());
    }

}
