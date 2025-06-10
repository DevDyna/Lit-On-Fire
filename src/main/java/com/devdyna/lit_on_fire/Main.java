package com.devdyna.lit_on_fire;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "lit_on_fire";

    public static String langString = "actionbar." + MODID + ".";

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        //TODO ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configBuilder);
        MinecraftForge.EVENT_BUS.register(new ClickEvent());
    }

}
