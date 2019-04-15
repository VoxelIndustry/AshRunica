package net.vi.ashrunica.common.init;

import net.minecraft.util.ResourceLocation;
import net.vi.ashrunica.AshRunica;
import net.voxelindustry.steamlayer.tile.descriptor.ModularTiles;

public class ARTiles
{
    private static ModularTiles instance;

    public static ModularTiles getModularInstance() {
        if (instance == null)
            instance = ModularTiles.instance(AshRunica.MODID);
        return instance;
    }

    public static void preInit()
    {
        getModularInstance().addTile(new ResourceLocation(AshRunica.MODID, "runedesigner"));

        getModularInstance().addTile(new ResourceLocation(AshRunica.MODID, "runesynthetizer"));

        getModularInstance().preloadTiles();
    }

    public static void init()
    {
        getModularInstance().loadTiles();
    }
}
