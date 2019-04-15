package net.vi.ashrunica.client.gui;

import net.vi.ashrunica.AshRunica;
import net.voxelindustry.brokkgui.paint.Texture;
import net.voxelindustry.brokkgui.wrapper.container.BrokkGuiContainer;
import net.voxelindustry.steamlayer.container.BuiltContainer;

public class GuiRuneSynthetizer extends BrokkGuiContainer<BuiltContainer>
{

    public GuiRuneSynthetizer(BuiltContainer container)
    {
        super(container);
        this.setSize(172, 172);
        this.getMainPanel().setBackgroundTexture(new Texture(AshRunica.MODID + ":textures/gui/runedesigner.png"));
    }
}
