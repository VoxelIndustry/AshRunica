package net.vi.ashrunica.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.tile.TileRuneSynthetizer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockRuneSynthetizer extends Block implements ITileEntityProvider
{
    public BlockRuneSynthetizer(Material material)
    {
        super(material);
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
        return new TileRuneSynthetizer();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn.isSneaking())
            return false;
        playerIn.openGui(AshRunica.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
