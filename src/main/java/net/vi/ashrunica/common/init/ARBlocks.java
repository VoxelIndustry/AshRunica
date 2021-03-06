package net.vi.ashrunica.common.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.block.BlockFactory;
import net.vi.ashrunica.common.block.BlockRuneDesigner;
import net.vi.ashrunica.common.block.BlockRuneSynthetizer;
import net.vi.ashrunica.common.block.BlockRuneTest;
import net.vi.ashrunica.common.tile.TileRuneDesigner;
import net.vi.ashrunica.common.tile.TileRuneSynthetizer;
import net.vi.ashrunica.common.tile.TileRuneTest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@GameRegistry.ObjectHolder(AshRunica.MODID)
public class ARBlocks
{
    public static Map<Block, ItemBlock> BLOCKS;

    public static void init()
    {
        BLOCKS = new LinkedHashMap<>();

        MinecraftForge.EVENT_BUS.register(new ARBlocks());

        registerBlock(BlockFactory.init(new BlockRuneTest(Material.CIRCUITS), "testrune"));

        registerBlock(BlockFactory.init(new BlockRuneDesigner(Material.WOOD), "runedesigner"));

        registerBlock(BlockFactory.init(new BlockRuneSynthetizer(Material.ROCK), "runesynthetizer"));

        registerTile(TileRuneTest.class, "testrune");

        registerTile(TileRuneDesigner.class, "runedesigner");

        registerTile(TileRuneSynthetizer.class, "runesynthetizer");
    }

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BLOCKS.keySet().toArray(new Block[0]));
    }

    private static <T extends Block> void registerBlock(T block)
    {
        registerBlock(block, ItemBlock::new);
    }

    private static <T extends Block> void registerBlock(T block, Function<T, ItemBlock> supplier)
    {
        ItemBlock supplied = supplier.apply(block);
        supplied.setRegistryName(block.getRegistryName());

        BLOCKS.put(block, supplied);
    }

    private static void registerTile(Class<? extends TileEntity> c, String name)
    {
        GameRegistry.registerTileEntity(c, new ResourceLocation(AshRunica.MODID, name));
    }
}
