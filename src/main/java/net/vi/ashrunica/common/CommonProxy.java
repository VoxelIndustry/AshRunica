package net.vi.ashrunica.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.vi.ashrunica.common.init.ASBlocks;
import net.vi.ashrunica.common.init.ASItems;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent e)
    {
        ASBlocks.init();
        ASItems.init();
    }

    public void init(FMLInitializationEvent e)
    {

    }

    public void postInit(FMLPostInitializationEvent e)
    {
    }

    public void registerItemRenderer(Item item, int meta)
    {

    }
}
