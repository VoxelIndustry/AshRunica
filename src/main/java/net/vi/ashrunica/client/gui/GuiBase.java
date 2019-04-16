package net.vi.ashrunica.client.gui;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.voxelindustry.brokkgui.wrapper.container.BrokkGuiContainer;
import net.voxelindustry.steamlayer.container.BuiltContainer;
import net.voxelindustry.steamlayer.container.IContainerProvider;
import net.voxelindustry.steamlayer.tile.TileBase;

public class GuiBase<T extends TileBase & IContainerProvider> extends BrokkGuiContainer<BuiltContainer>
{
    @Getter
    private T tile;

    public GuiBase(T tile)
    {
        super(tile.createContainer(Minecraft.getMinecraft().player));

        this.tile = tile;
    }
}
