package com.devdyna.lit_on_fire;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class LevelUtil {
    public static boolean isDimension(Level level, ResourceKey<Level> dim) {
        return level.dimension().equals(dim);
    }

    public static void SimplePlaceBlock(Level level, BlockPos pos, Block block) {
        level.setBlock(pos, block.defaultBlockState(), 32);
    }

    public static int ValidFaces(BlockPos pos, Level level, TagKey<Block> tag) {
        BlockPos[] dir = { pos.above(), pos.below(), pos.north(), pos.south(), pos.east(), pos.west() };
        int value = 0;
        for (BlockPos face : dir) {
            value += level.getBlockState(face).is(tag) ? 1 : 0;
        }
        return value;
    }

    public static List<Holder<Block>> ResourceByTag(TagKey<Block> tag) {
        return BuiltInRegistries.BLOCK.getOrCreateTag(tag).stream().toList();
    }

    public static Block ResourceByTag(TagKey<Block> tag, int index) {
        return ResourceByTag(tag).get(index).value();
    }

    public static int getSizeTag(TagKey<Block> tag) {
        return ResourceByTag(tag).size() - 1;
    }

    public static void popItemFromPos(Level level, double x, double y, double z, ItemStack itemStack) {
        ItemEntity itementity = new ItemEntity(level,
                x,
                y,
                z,
                itemStack);
        level.addFreshEntity(itementity);
    }

    public static void popItemFromPos(Level level, int x, int y, int z, ItemStack itemStack) {
        popItemFromPos(level, (double) x, (double) y, (double) z, itemStack);
    }

    public static void popItemFromPos(LevelAccessor level, double x, double y, double z, ItemStack itemStack) {
        popItemFromPos((Level) level, x, y, z, itemStack);
    }

    // example
    /**
     * 
     * @param level
     * @param ModName          "minecraft"
     * @param resourcelocation "blocks/stone"
     * @return
     */
    public static List<ItemStack> getItemStackFromLootTable(ServerLevel level, String ModName,
            String resourcelocation) {
        LootTable lootTable = level.getServer().reloadableRegistries()
                .getLootTable(ResourceKey
                        .create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(
                                ModName, resourcelocation)));
        return lootTable.getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY));
    }

    /**
     * @return a value [0 , max] inclusive
     */
    public static int getRandomValue(int max, Level level) {
        if (max <= 0)
            return 1;

        return level.random.nextInt(max) + 1;
    }

    public static boolean chance(int value, Level level) {
        if (value == 0)
            return false;

        return getRandomValue(100, level) <= value;
    }

    public static Direction randomDirection(Level l, Direction[] d) {
        return d[l.random.nextInt(d.length)];
    }

    public static boolean checkBlockPoses(Level l, BlockPos[] p1,Block b){
        for(BlockPos p2 : p1){
            if(!l.getBlockState(p2).is(b))
            return false;
        }
        return true;
    }

    public static boolean checkBlockPoses(Level l, BlockPos[] p1,TagKey<Block> b){
        for(BlockPos p2 : p1){
            if(!l.getBlockState(p2).is(b))
            return false;
        }
        return true;
    }

}
