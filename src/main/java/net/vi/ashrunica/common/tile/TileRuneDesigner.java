package net.vi.ashrunica.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.init.ARTiles;
import net.voxelindustry.steamlayer.container.BuiltContainer;
import net.voxelindustry.steamlayer.container.ContainerBuilder;
import net.voxelindustry.steamlayer.container.IContainerProvider;
import net.voxelindustry.steamlayer.tile.TileBase;
import net.voxelindustry.steamlayer.tile.modular.TileModule;
import net.voxelindustry.steamlayer.tile.modular.impl.InventoryModule;
import net.voxelindustry.steamlayer.tile.modular.impl.TileModular;
import net.voxelindustry.steamlayer.tile.modular.impl.TileTickingModular;

public class TileRuneDesigner extends TileTickingModular implements IContainerProvider
{
    public TileRuneDesigner()
    {
        super(AshRunica.MODID, ARTiles.getModularInstance().get("runedesigner"));
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        return super.writeToNBT(tag);
    }

    @Override
    protected void reloadModules()
    {
        super.reloadModules();

        InventoryModule inventoryModule = new InventoryModule(this);
        inventoryModule.addBasic(8);

        this.addModule(inventoryModule);
    }

    @Override
    public BuiltContainer createContainer(EntityPlayer player)
    {
        return new ContainerBuilder("runedesigner", player).player(player).inventory().hotbar()
                .tile(this.getModule(InventoryModule.class).getInventory("basic"))
                .slotLine(0, 0, 0, 8, EnumFacing.Axis.X).create();
    }
}
