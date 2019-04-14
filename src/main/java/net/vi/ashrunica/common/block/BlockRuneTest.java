package net.vi.ashrunica.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.vi.ashrunica.common.tile.TileRuneTest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockRuneTest extends Block implements ITileEntityProvider
{
    public BlockRuneTest(Material material)
    {
        super(material);
    }

    @Override
    @Nonnull
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World world, int meta)
    {
        return new TileRuneTest();
    }
}
