package net.vi.ashrunica.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.gui.GuiHandler;
import net.vi.ashrunica.common.init.ARTiles;
import net.vi.ashrunica.common.init.ARBlocks;
import net.vi.ashrunica.common.init.ARItems;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent e)
    {
        ARBlocks.init();
        ARItems.init();
        ARTiles.preInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(AshRunica.instance, new GuiHandler());
    }

    public void init(FMLInitializationEvent e)
    {
        ARTiles.init();
    }

    public void postInit(FMLPostInitializationEvent e)
    {
    }

    public void registerItemRenderer(Item item, int meta)
    {

    }
}
