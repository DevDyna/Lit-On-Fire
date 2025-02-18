package com.devdyna.lit_on_fire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ClickEvent {

    @SubscribeEvent
    public void TorchclickOnCampfire(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!state.is(BlockTags.CAMPFIRES))
            return;

        if (state.getValue(BlockStateProperties.LIT).booleanValue())
            return;

        if (state.getValue(BlockStateProperties.WATERLOGGED).booleanValue())
            return;

        Player player = event.getEntity();
        BlockPos top = pos.above();
        ItemStack item = event.getItemStack();
        BlockPos[] checkpos = {
                top,
                top.north(),
                top.south(),
                top.east(),
                top.west()
        };

        if (event.getHand().equals(InteractionHand.MAIN_HAND)
                && // Config.I_GLOB.get() ? item.is(Tags.ITEM_TORCHES): true
                item.is(Tags.ITEM_TORCHES)

        ) {

            // if (Config.C_GLOB.get())
            player.swing(InteractionHand.MAIN_HAND);

            if (LevelUtil.checkBlockPoses(level, checkpos, BlockTags.AIR)) {

                // if (Config.A_BOOL.get()) {
                // if (LevelUtil.chance(
                // Config.A_INVERT.get() ? 100 - Config.A_CHANCE.get() : Config.A_CHANCE.get(),
                // level)) {
                // level.setBlockAndUpdate(pos,
                // state.setValue(BlockStateProperties.LIT, true));
                // }
                // } else {
                level.setBlockAndUpdate(pos,
                        state.setValue(BlockStateProperties.LIT, true));
                // }

                // if (Config.P_BOOL_GLOB.get() && Config.P_BOOL_ON.get())
                level.addParticle(ParticleTypes.LAVA, true, pos.getX() + 0.5,
                        pos.getY() + 1.75,
                        pos.getZ() + 0.5, 0, 0, 0);

                // if (Config.S_BOOL_GLOB.get() && Config.S_BOOL_ON.get())
                level.playLocalSound(pos.getX(), pos.getY(),
                        pos.getZ(), SoundEvents.LODESTONE_COMPASS_LOCK, SoundSource.AMBIENT, 100,
                        0.75f, true);

                // if (Config.T_BOOL_GLOB.get() && Config.T_ON.get())
                player.displayClientMessage(Component.translatable(Main.langString + "valid"),
                        true);
            } else {

                // if (Config.P_BOOL_GLOB.get() && Config.P_BOOL_OFF.get())
                // level.addParticle(ParticleTypes.ANGRY_VILLAGER, true, pos.getX() + 0.5,
                // pos.getY() + 1.75,
                // pos.getZ() + 0.5, 0, 0, 0);

                // if (Config.S_BOOL_GLOB.get() && Config.S_BOOL_OFF.get())
                level.playLocalSound(pos.getX(), pos.getY(),
                        pos.getZ(), SoundEvents.VAULT_INSERT_ITEM_FAIL, SoundSource.AMBIENT, 100,
                        0.75f, true);

                // if (Config.T_BOOL_GLOB.get() && Config.T_ON.get())
                player.displayClientMessage(Component.translatable(Main.langString + "invalid"),
                        true);

            }

        }

    }

}
