package com.devdyna.lit_on_fire;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.config.ModConfig;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "lit_on_fire";

    public  static String langString = "actionbar."+MODID+".";

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
        NeoForge.EVENT_BUS.register(new ClickEvent());
    }

}
