package com.devdyna.lit_on_fire;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // ---------------------------------------------------------------//

    public static final ModConfigSpec.BooleanValue CONSUME_ITEM = BUILDER
            .comment("Items can be consumed on activation")
            .define("item_consume", false);

    public static final ModConfigSpec.BooleanValue IS_WATERLOGGED = BUILDER
            .comment("Enable checking waterlog property to lit")
            .define("check_waterlogging", true);

    public static final ModConfigSpec.BooleanValue SWING = BUILDER
            .comment("Enable item swing animation")
            .define("swing", true);

    public static final ModConfigSpec.BooleanValue PARTICLES_ON = BUILDER
            .comment("Enable particles on activation ")
            .define("particles_activation", true);

    public static final ModConfigSpec.BooleanValue SOUND_ON = BUILDER
            .comment("Enable sounds on activation")
            .define("sound_activation", true);

    public static final ModConfigSpec.BooleanValue ACTIONBAR_ON = BUILDER
            .comment("Enable actionbar tooltip on activation")
            .define("actionbar_activation", true);

    public static final ModConfigSpec.BooleanValue CHANCE_TO_LIT = BUILDER
            .comment("Enable chance on activation")
            .define("chance_to_lit", true);

    public static final ModConfigSpec.IntValue CHANCE_TO_LIT_VALUE = BUILDER
            .comment("Chance of success 1/X")
            .defineInRange("chance_to_lit_value", 50, 1, 100);

    public static final ModConfigSpec.BooleanValue CHANCE_FAIL = BUILDER
            .comment("Enable chance fail sound")
            .define("chance_to_lit_fail", true);

    public static final ModConfigSpec.BooleanValue INVALID_SOUND = BUILDER
            .comment("Enable sound on invalid placement")
            .define("invalid_sound", true);

    public static final ModConfigSpec.BooleanValue INVALID_TIP = BUILDER
            .comment("Enable action bar on invalid placement")
            .define("invalid_actionbar", true);

    // ---------------------------------------------------------------//

    static final ModConfigSpec CONFIG_SPEC = BUILDER.build();
}