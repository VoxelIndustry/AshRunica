package net.vi.ashrunica.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.vi.ashrunica.AshRunica;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(AshRunica.MODID)
public class ARItems
{
    public static List<Item> ITEMS;

    public static void init()
    {
        ITEMS = new ArrayList<>();
        ITEMS.addAll(ARBlocks.BLOCKS.values());

        MinecraftForge.EVENT_BUS.register(new ARItems());
    }

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }

    static void registerItem(Item item)
    {
        ITEMS.add(item);
    }
}
