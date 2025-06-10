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

        if (Config.IS_WATERLOGGED.get())
            if (state.getValue(BlockStateProperties.WATERLOGGED).booleanValue())
                return;

        Player player = event.getEntity();
        BlockPos top = pos.above();
        ItemStack item = event.getItemStack();
        BlockPos[] checkpos = { top, top.north(), top.south(), top.east(), top.west() };

        if (event.getHand().equals(InteractionHand.MAIN_HAND) && item.is(Tags.ITEM_TORCHES)) {

            if (Config.CONSUME_ITEM.get())
                item.shrink(1);

            if (Config.SWING.get())
                player.swing(InteractionHand.MAIN_HAND);

            if (LevelUtil.checkBlockPoses(level, checkpos, BlockTags.AIR)) {

                if (Config.CHANCE_TO_LIT.get()) {
                    if (LevelUtil.chance(Config.CHANCE_TO_LIT_VALUE.get(), level))
                        Success(pos, level, state, player);
                    else if (Config.CHANCE_FAIL.get())
                        level.playLocalSound(pos.getX(), pos.getY(),
                                pos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.AMBIENT, 100,
                                0.75f, true);
                } else
                    Success(pos, level, state, player);

            } else {

                if (Config.INVALID_SOUND.get())
                    level.playLocalSound(pos.getX(), pos.getY(),
                            pos.getZ(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.AMBIENT, 100,
                            0.75f, true);

                if (Config.INVALID_TIP.get())
                    player.displayClientMessage(Component.translatable(Main.langString + "invalid"),
                            true);

            }

        }

    }

    public static void Success(BlockPos pos, Level level, BlockState state, Player player) {
        if(!level.isClientSide)
        level.setBlockAndUpdate(pos,
                state.setValue(BlockStateProperties.LIT, true));
        

        if (Config.PARTICLES_ON.get())
            level.addParticle(ParticleTypes.LAVA, true, pos.getX() + 0.5,
                    pos.getY() + 1.75,
                    pos.getZ() + 0.5, 0, 0, 0);

        if (Config.SOUND_ON.get())
            level.playLocalSound(pos.getX(), pos.getY(),
                    pos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.AMBIENT, 100,
                    0.75f, true);

        if (Config.ACTIONBAR_ON.get())
            player.displayClientMessage(Component.translatable(Main.langString + "valid"),
                    true);
    }
}