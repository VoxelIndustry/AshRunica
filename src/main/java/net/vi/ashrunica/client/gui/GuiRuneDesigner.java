package net.vi.ashrunica.client.gui;

import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.tile.TileRuneDesigner;
import net.voxelindustry.brokkgui.paint.Texture;

public class GuiRuneDesigner extends GuiBase<TileRuneDesigner>
{
    public GuiRuneDesigner(TileRuneDesigner designer)
    {
        super(designer);
        this.setSize(172, 172);
        this.getMainPanel().setBackgroundTexture(new Texture(AshRunica.MODID + ":textures/gui/runedesigner.png"));
    }
}
