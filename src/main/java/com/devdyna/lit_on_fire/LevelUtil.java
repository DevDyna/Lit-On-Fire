package com.devdyna.lit_on_fire;

import java.util.Arrays;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class LevelUtil {

    /**
     * @return a value [0 , max] inclusive
     */
    public static int getRandomValue(int max, Level level) {
        if (max <= 0)
            return 1;

        return level.getRandom().nextInt(max) + 1;
    }

    public static boolean chance(int value, Level level) {
        if (value == 0)
            return false;

        return getRandomValue(100, level) <= value;
    }

    public static boolean checkBlockPoses(Level l, BlockPos[] p1, Block... blocks) {
        for (BlockPos p2 : p1) {
            if (!Arrays.asList(blocks).contains(l.getBlockState(p2).getBlock()))
                return false;
        }
        return true;
    }

}
