package net.vi.ashrunica.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.vi.ashrunica.client.gui.GuiRuneDesigner;
import net.vi.ashrunica.client.gui.GuiRuneSynthetizer;
import net.vi.ashrunica.common.tile.TileRuneDesigner;
import net.vi.ashrunica.common.tile.TileRuneSynthetizer;
import net.voxelindustry.brokkgui.wrapper.impl.BrokkGuiManager;
import net.voxelindustry.steamlayer.container.IContainerProvider;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (tile instanceof IContainerProvider)
            return ((IContainerProvider) tile).createContainer(player);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        switch (GuiType.values()[ID])
        {
            case RUNE_DESIGNER:
                return BrokkGuiManager.getBrokkGuiContainer(new GuiRuneDesigner((TileRuneDesigner) tile));
            case RUNE_SYNTHETIZER:
                return BrokkGuiManager.getBrokkGuiContainer(new GuiRuneSynthetizer((TileRuneSynthetizer) tile));
        }
        return null;
    }
}
