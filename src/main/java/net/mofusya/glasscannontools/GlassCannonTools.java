package net.mofusya.glasscannontools;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mofusya.glasscannontools.config.ServerConfig;
import org.slf4j.Logger;

@Mod(GlassCannonTools.MOD_ID)
public class GlassCannonTools
{
    public static final String MOD_ID = "glasscannontools";
    private static final Logger LOGGER = LogUtils.getLogger();

    public GlassCannonTools()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, MOD_ID + "/server.toml");
    }
}
