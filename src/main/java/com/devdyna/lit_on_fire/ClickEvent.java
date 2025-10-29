package com.devdyna.lit_on_fire;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class ClickEvent {

    @SubscribeEvent
    public void TorchclickOnCampfire(RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Player player = event.getEntity();
        BlockPos top = pos.above();
        ItemStack item = event.getItemStack();
        BlockPos[] checkpos = { top, top.north(), top.south(), top.east(), top.west() };
        InteractionHand hand = event.getHand();

        if (!hand.equals(InteractionHand.MAIN_HAND) || !item.is(Tags.CAN_LIT_BLOCKS))
            return;

        // found a block to lit up
        if (state.is(Tags.CAN_BE_LIT_UP)) {

            if (state.getValue(BlockStateProperties.LIT).booleanValue())
                return;

            if (Config.IS_WATERLOGGED.get())
                if (state.getValue(BlockStateProperties.WATERLOGGED).booleanValue())
                    return;

            if (Config.CONSUME_ITEM.get())
                item.shrink(1);

            if (Config.SWING.get())
                player.swing(InteractionHand.MAIN_HAND);

            if (Config.REQUIRE_SPACE_TO_LIT_BLOCK.get()
                    ? LevelUtil.checkBlockPoses(level, checkpos, Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR)
                    : true) {

                if (Config.CHANCE_TO_LIT.get()) {
                    if (LevelUtil.chance(Config.CHANCE_TO_LIT_VALUE.get(), level))
                        Success(event, false);
                    else if (Config.CHANCE_FAIL.get())
                        level.playLocalSound(pos.getX(), pos.getY(),
                                pos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.AMBIENT, 100,
                                0.75f, true);
                } else
                    Success(event, false);

            } else {

                if (Config.INVALID_SOUND.get())
                    level.playLocalSound(pos.getX(), pos.getY(),
                            pos.getZ(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.AMBIENT, 100,
                            0.75f, true);

                if (Config.INVALID_TIP.get())
                    player.displayClientMessage(Component.translatable(Main.langString + "invalid"),
                            true);

            }
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);

        }

        // found a portal shape
        if (state.is(Blocks.OBSIDIAN) && Config.CAN_LIT_PORTAL.get() && !player.isShiftKeyDown()) {

            if (checkPortal(level, top, item)) {
                if (Config.CONSUME_ITEM.get())
                    item.shrink(1);

                if (Config.SWING.get())
                    player.swing(InteractionHand.MAIN_HAND);

                Success(event, true);
            }

        }

    }

    public static void Success(RightClickBlock event, boolean isPortal) {

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Player player = event.getEntity();

        if (!level.isClientSide)
            if (!isPortal)
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

        event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
        event.setCanceled(true);

    }

    public static boolean checkPortal(Level level, BlockPos pos, ItemStack item) {
        if (!level.isClientSide)
            for (Direction.Axis axis : Direction.Axis.values()) {
                Optional<PortalShape> portal = PortalShape.findEmptyPortalShape(level, pos, axis);
                if (portal.isPresent()) {
                    portal.get().createPortalBlocks();
                    return true;
                }
            }
        return false;
    }

}