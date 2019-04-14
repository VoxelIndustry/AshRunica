package net.vi.ashrunica.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.vi.ashrunica.client.gui.GuiRuneDesigner;
import net.voxelindustry.brokkgui.wrapper.impl.BrokkGuiManager;
import net.voxelindustry.steamlayer.container.IContainerProvider;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 0)
            return ((IContainerProvider) tileEntity).createContainer(player);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 0)
            return BrokkGuiManager.getBrokkGuiContainer(new GuiRuneDesigner(((IContainerProvider) tileEntity).createContainer(player)));
        return null;
    }
}
