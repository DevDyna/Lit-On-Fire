package com.devdyna.lit_on_fire;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        // ---------------------------------------------------------------//

        public static final ForgeConfigSpec.BooleanValue CONSUME_ITEM = BUILDER
                        .comment("Items can be consumed on activation")
                        .define("item_consume", false);

        public static final ForgeConfigSpec.BooleanValue IS_WATERLOGGED = BUILDER
                        .comment("Enable checking waterlog property to lit")
                        .define("check_waterlogging", true);

        public static final ForgeConfigSpec.BooleanValue REQUIRE_SPACE_TO_LIT_BLOCK = BUILDER
                        .comment("Enable checking required space to lit")
                        .define("check_require_space", true);

        public static final ForgeConfigSpec.BooleanValue SWING = BUILDER
                        .comment("Enable item swing animation")
                        .define("swing", true);

        public static final ForgeConfigSpec.BooleanValue PARTICLES_ON = BUILDER
                        .comment("Enable particles on activation ")
                        .define("particles_activation", true);

        public static final ForgeConfigSpec.BooleanValue SOUND_ON = BUILDER
                        .comment("Enable sounds on activation")
                        .define("sound_activation", true);

        public static final ForgeConfigSpec.BooleanValue ACTIONBAR_ON = BUILDER
                        .comment("Enable actionbar tooltip on activation")
                        .define("actionbar_activation", true);

        public static final ForgeConfigSpec.BooleanValue CHANCE_TO_LIT = BUILDER
                        .comment("Enable chance on activation")
                        .define("chance_to_lit", true);

        public static final ForgeConfigSpec.IntValue CHANCE_TO_LIT_VALUE = BUILDER
                        .comment("Chance of success 1/X")
                        .defineInRange("chance_to_lit_value", 50, 1, 100);

        public static final ForgeConfigSpec.BooleanValue CHANCE_FAIL = BUILDER
                        .comment("Enable chance fail sound")
                        .define("chance_to_lit_fail", true);

        public static final ForgeConfigSpec.BooleanValue INVALID_SOUND = BUILDER
                        .comment("Enable sound on invalid placement")
                        .define("invalid_sound", true);

        public static final ForgeConfigSpec.BooleanValue INVALID_TIP = BUILDER
                        .comment("Enable action bar on invalid placement")
                        .define("invalid_actionbar", true);

        public static final ForgeConfigSpec.BooleanValue CAN_LIT_PORTAL = BUILDER
                        .comment("Enable event to lit nether portals")
                        .define("activation_portal", true);

        // ---------------------------------------------------------------//

        static final ForgeConfigSpec CONFIG_SPEC = BUILDER.build();
}
