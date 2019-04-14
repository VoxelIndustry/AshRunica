package net.vi.ashrunica.common.block;

import net.minecraft.block.Block;
import net.vi.ashrunica.AshRunica;

public class BlockFactory
{
    public static Block init(Block block, String name)
    {
        block.setRegistryName(AshRunica.MODID, name);
        block.setTranslationKey(AshRunica.MODID + ":" + name);
        block.setCreativeTab(AshRunica.TAB_ALL);
        return block;
    }
}
