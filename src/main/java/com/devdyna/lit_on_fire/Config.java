// package com.devdyna.lit_on_fire;

// import net.neoforged.neoforge.common.ModConfigSpec;

// public class Config {
//     private static final ModConfigSpec.Builder B = new ModConfigSpec.Builder();

//     // ---------------------------------------------------------------//
//     // tooltip
//     public static final ModConfigSpec.BooleanValue T_BOOL_GLOB = B
//             .comment("Enable/Disable any tooltips")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue T_ON = B
//             .comment("Enable/Disable tooltip on campfire successful actived")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue T_OFF = B
//             .comment("Enable/Disable tooltip on campfire failed to active")
//             .define("Status", true);

//     // ---------------------------------------------------------------//
//     // activation chance
//     public static final ModConfigSpec.BooleanValue A_BOOL = B
//             .comment("Enable/Disable random chance on campfire activation")
//             .define("Random Activation", false);

//     public static final ModConfigSpec.IntValue A_CHANCE = B
//             .comment("⚠ Only work when Random Activation still enable ⚠")
//             .comment("Chance of success on clicking")
//             .defineInRange("Chance", 50, 1, 100);

//     public static final ModConfigSpec.BooleanValue A_INVERT = B
//             .comment("⚠ Only work when Random Activation still enable ⚠")
//             .comment("Invert the chance of success on clicking")
//             .define("Invert Chance", false);

//     // ---------------------------------------------------------------//
//     // sound
//     public static final ModConfigSpec.BooleanValue S_BOOL_GLOB = B
//             .comment("Enable/Disable sound effects")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue S_BOOL_ON = B
//             .comment("Enable/Disable sound effects on campfire activation")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue S_BOOL_OFF = B
//             .comment("Enable/Disable sound effects on campfire failed to active")
//             .define("Status", true);

//     // ---------------------------------------------------------------//
//     // particles
//     public static final ModConfigSpec.BooleanValue P_BOOL_GLOB = B
//             .comment("Enable/Disable particles effects")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue P_BOOL_ON = B
//             .comment("Enable/Disable particles effects on campfire activation")
//             .define("Status", true);

//     public static final ModConfigSpec.BooleanValue P_BOOL_OFF = B
//             .comment("Enable/Disable particles effects on campfire failed to active")
//             .define("Status", false);
//     // ---------------------------------------------------------------//
//     // item action
//     public static final ModConfigSpec.BooleanValue I_GLOB = B
//             .comment("Enable/Disable item required to active campfires")
//             .define("Status", true);
//     // ---------------------------------------------------------------//
//     // player animation
//     public static final ModConfigSpec.BooleanValue C_GLOB = B
//             .comment("Enable/Disable player swing")
//             .define("Status", true);
//     // ---------------------------------------------------------------//

//     static final ModConfigSpec ZCK = B.build();
// }
