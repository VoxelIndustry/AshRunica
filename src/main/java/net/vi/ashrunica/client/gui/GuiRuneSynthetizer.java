package net.vi.ashrunica.client.gui;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.tile.TileRuneSynthetizer;
import net.voxelindustry.brokkgui.data.RectBox;
import net.voxelindustry.brokkgui.element.GuiLabel;
import net.voxelindustry.brokkgui.element.input.GuiButton;
import net.voxelindustry.brokkgui.element.input.GuiToggleButton;
import net.voxelindustry.brokkgui.paint.Color;
import net.voxelindustry.brokkgui.paint.Texture;
import net.voxelindustry.brokkgui.panel.GuiAbsolutePane;
import net.voxelindustry.brokkgui.wrapper.container.BrokkGuiContainer;
import net.voxelindustry.steamlayer.container.BuiltContainer;
import net.voxelindustry.steamlayer.network.action.ServerActionBuilder;
import scala.collection.concurrent.Debug;

public class GuiRuneSynthetizer extends GuiBase<TileRuneSynthetizer>
{

    public GuiRuneSynthetizer(TileRuneSynthetizer tile)
    {
        super(tile);
        this.setSize(176, 166);

        GuiAbsolutePane mainPanel = new GuiAbsolutePane();
        this.setMainPanel(mainPanel);

        this.getMainPanel().setBackgroundTexture(new Texture(AshRunica.MODID + ":textures/gui/runesynthetizer.png"));
        //this.getContainer().inventorySlots.get(0).putStack(new ItemStack(Item.getItemById(50)));

        GuiButton guiIncreaseButton = new GuiButton();
        //guiIncreaseButton.setBackgroundColor(Color.YELLOW);
        guiIncreaseButton.setID("increase-button");
        guiIncreaseButton.setSize(10, 15);
        guiIncreaseButton.setOnActionEvent(e -> editNumberCopies(true));

        GuiButton guiDecreaseButton = new GuiButton();
        //guiDecreaseButton.setBackgroundColor(Color.BLACK);
        guiDecreaseButton.setID("decrease-button");
        guiDecreaseButton.setSize(10, 15);
        guiDecreaseButton.setOnActionEvent(e -> editNumberCopies(false));

        GuiToggleButton guiBuildButton = new GuiToggleButton();
        guiBuildButton.setID("build-button");
        guiBuildButton.setSize(34, 11);
        guiBuildButton.getSelectedProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue)
                startBuilding();
            else
                cancelBuilding();
        });

        GuiLabel copiesLabel = new GuiLabel();
        copiesLabel.setID("copies-label");
        copiesLabel.setTextPadding(RectBox.build().top(1).create());
        copiesLabel.setSize(20, 12);

        mainPanel.addChild(guiIncreaseButton, 98, 63);
        mainPanel.addChild(guiDecreaseButton, 68, 63);
        mainPanel.addChild(copiesLabel, 78, 64);
        mainPanel.addChild(guiBuildButton, 72, 49);

        this.getContainer().addSyncCallback("SYNC_CURRENT_COPIES", sync ->
        {
            guiDecreaseButton.setDisabled(getTile().getCurrentCopiesNumber() <= 1);
            copiesLabel.setText(String.valueOf(getTile().getCurrentCopiesNumber()));
        });

        this.getContainer().addSyncCallback("SYNC_BUILD_STATUS", sync ->
                guiBuildButton.setSelected(getTile().isBuildStatus()));

        this.addStylesheet("/assets/ashrunica/css/runesynthetizer.css");
    }

    private void editNumberCopies(boolean increase)
    {
        new ServerActionBuilder("MODIFYNUMBERCOPIES").withInt("number", increase ? 1 : -1).toTile(getTile()).send();
    }

    private void startBuilding()
    {
        new ServerActionBuilder("TRIGGERSYNTHETIZER").withBoolean("setbuildstatus", true).toTile(getTile()).send();
    }

    private void cancelBuilding()
    {
        new ServerActionBuilder("TRIGGERSYNTHETIZER").withBoolean("setbuildstatus", false).toTile(getTile()).send();
    }
}
