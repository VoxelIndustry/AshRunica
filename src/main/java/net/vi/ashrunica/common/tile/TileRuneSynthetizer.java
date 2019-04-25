package net.vi.ashrunica.common.tile;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.init.ARTiles;
import net.voxelindustry.steamlayer.container.BuiltContainer;
import net.voxelindustry.steamlayer.container.ContainerBuilder;
import net.voxelindustry.steamlayer.container.IContainerProvider;
import net.voxelindustry.steamlayer.network.action.ActionSender;
import net.voxelindustry.steamlayer.network.action.IActionReceiver;
import net.voxelindustry.steamlayer.tile.modular.impl.InventoryModule;
import net.voxelindustry.steamlayer.tile.modular.impl.TileTickingModular;

public class TileRuneSynthetizer extends TileTickingModular implements IContainerProvider, IActionReceiver
{
    @Getter
    @Setter
    int currentCopiesNumber = 1;
    @Getter
    @Setter
    boolean buildStatus = false;
    @Getter
    @Setter
    float buildProgress = 0f;

    public TileRuneSynthetizer()
    {
        super(AshRunica.MODID, ARTiles.getModularInstance().get("runesynthetizer"));
    }

    @Override
    public void update()
    {
        super.update();
        if (isClient())
            return;
        if (buildProgress >= 1f)
        {
            buildStatus = false;
            //CreateARune() here TODO
            buildProgress = 0f;
        }
        if (buildStatus == true)
            buildProgress += 0.05;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        currentCopiesNumber = tag.getInteger("currentCopies");
        buildStatus = tag.getBoolean("buildStatus");
        buildProgress = tag.getFloat("buildProgress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("currentCopies", currentCopiesNumber);
        tag.setBoolean("buildStatus", buildStatus);
        tag.setFloat("buildProgress", buildProgress);

        return super.writeToNBT(tag);
    }

    @Override
    protected void reloadModules()
    {
        super.reloadModules();

        InventoryModule inventoryModule = new InventoryModule(this, 2);

        this.addModule(inventoryModule);
    }

    @Override
    public BuiltContainer createContainer(EntityPlayer player)
    {
        return new ContainerBuilder("runesynthetizer", player).player(player).inventory(8, 84).hotbar(8, 142)
                .tile(this.getModule(InventoryModule.class).getInventory("basic"))
                .slot(0, 35, 46)
                .slot(1, 125, 46)
                .sync()
                .syncInteger(this::getCurrentCopiesNumber, this::setCurrentCopiesNumber, "SYNC_CURRENT_COPIES")
                .syncBoolean(this::isBuildStatus, this::setBuildStatus, "SYNC_BUILD_STATUS")
                .syncFloat(this::getBuildProgress, this::setBuildProgress, "SYNC_BUILD_PROGRESS")
                .create();
    }

    @Override
    public void handle(ActionSender sender, String actionID, NBTTagCompound payload)
    {
        if ("MODIFYNUMBERCOPIES".equals(actionID))
        {
            currentCopiesNumber = Math.max(payload.getInteger("number") + currentCopiesNumber, 1);
        }

        if ("TRIGGERSYNTHETIZER".equals(actionID))
        {
            buildStatus = payload.getBoolean("setbuildstatus");
            if (buildStatus == false)
                buildProgress = 0f;
        }
    }
}
