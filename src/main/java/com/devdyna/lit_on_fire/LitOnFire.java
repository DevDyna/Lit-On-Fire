package com.devdyna.lit_on_fire;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
// import net.minecraft.network.chat.OutgoingChatMessage;
// import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

    public static final TagKey<Item> ITEM_TORCHES = TagKey.create(BuiltInRegistries.ITEM.key(),
            ResourceLocation.fromNamespaceAndPath(MODID, "can_lit_campfire"));

    public static final TagKey<Block> BLOCK_TORCHES = TagKey.create(BuiltInRegistries.BLOCK.key(),
            ResourceLocation.fromNamespaceAndPath(MODID, "can_lit_campfire"));

    public LitOnFire(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void TorchclickOnCampfire(PlayerInteractEvent.RightClickBlock event) {

        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        if (event.getHand().equals(InteractionHand.MAIN_HAND)
                && level.getBlockState(pos).is(BlockTags.CAMPFIRES)
                && event.getItemStack().is(ITEM_TORCHES)
                && !getState(event).getValue(BlockStateProperties.LIT).booleanValue()) {

            event.getEntity().swing(InteractionHand.MAIN_HAND);

            if (level.getBlockState(pos.above()).is(BlockTags.AIR)
                    && level.getBlockState(pos.above().north()).is(BlockTags.AIR)
                    && level.getBlockState(pos.above().south()).is(BlockTags.AIR)
                    && level.getBlockState(pos.above().east()).is(BlockTags.AIR)
                    && level.getBlockState(pos.above().west()).is(BlockTags.AIR)) {

                level.setBlockAndUpdate(pos,
                        getState(event).setValue(BlockStateProperties.LIT, true));
                level.addParticle(ParticleTypes.LAVA, true, pos.getX() + 0.5,
                        pos.getY() + 1.75,
                        pos.getZ() + 0.5, 0, 0, 0);

                level.playLocalSound(pos.getX(), pos.getY(),
                        pos.getZ(), SoundEvents.LODESTONE_COMPASS_LOCK, SoundSource.AMBIENT, 100,
                        0.75f, true);

                messageActionBar("valid");
            } else {

                level.playLocalSound(pos.getX(), pos.getY(),
                        pos.getZ(), SoundEvents.VAULT_INSERT_ITEM_FAIL, SoundSource.AMBIENT, 100,
                        0.75f, true);

                messageActionBar("invalid");
            }

        }

    }

    public BlockState getState(RightClickBlock event) {
        return event.getLevel().getBlockState(event.getPos());
    }

    @SuppressWarnings({ "resource", "null" })
    public void messageActionBar(String name) {
        Minecraft.getInstance().player.displayClientMessage(Component.translatable("actionbar.lit_on_fire." + name),
                true);
    }

    // //DEBUG ONLY
    // public void message(RightClickBlock event, String text) {
    // event.getEntity().createCommandSourceStack().sendChatMessage(
    // new OutgoingChatMessage.Player(
    // PlayerChatMessage.unsigned(event.getEntity().getUUID(), text)),
    // false, ChatType.bind(ChatType.CHAT, event.getEntity()));

    // }

}
