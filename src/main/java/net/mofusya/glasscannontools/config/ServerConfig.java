package net.mofusya.glasscannontools.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.mofusya.glasscannontools.GlassCannonTools;

public class ServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> DROP_MULTIPLIER;

    static {
        BUILDER.push(GlassCannonTools.MOD_ID + "-server");

        DROP_MULTIPLIER = BUILDER.comment("[default: 64]").define("drop_multiplier", 64);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}