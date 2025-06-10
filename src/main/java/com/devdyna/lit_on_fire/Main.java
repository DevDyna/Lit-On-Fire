package com.devdyna.lit_on_fire;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "lit_on_fire";

    public static String langString = "actionbar." + MODID + ".";

    public Main() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
        MinecraftForge.EVENT_BUS.register(new ClickEvent());
    }

}
