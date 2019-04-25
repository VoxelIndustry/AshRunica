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
import net.voxelindustry.brokkgui.panel.GuiPane;
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

        GuiLabel copiesLabel = new GuiLabel();
        copiesLabel.setID("copies-label");
        copiesLabel.setTextPadding(RectBox.build().top(1).create());
        copiesLabel.setSize(20, 12);

        GuiToggleButton guiBuildButton = new GuiToggleButton();
        guiBuildButton.setID("build-button");
        guiBuildButton.setSize(54, 11);
        guiBuildButton.getSelectedProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue)
                startBuilding();
            else
                cancelBuilding();
        });

        GuiPane progressPane = new GuiPane();
        progressPane.setID("progress-pane");
        progressPane.setSize(0, 11); // from 0 to 30 width

        GuiLabel buildButtonLabel = new GuiLabel();
        buildButtonLabel.setID("build-button-label");
        buildButtonLabel.setTextPadding(RectBox.build().top(2).create());
        buildButtonLabel.setSize(30, 9);

        mainPanel.addChild(guiIncreaseButton, 98, 63);
        mainPanel.addChild(guiDecreaseButton, 68, 63);
        mainPanel.addChild(copiesLabel, 78, 64);
        mainPanel.addChild(guiBuildButton, 61, 49);
        mainPanel.addChild(progressPane, 61, 49);
        mainPanel.addChild(buildButtonLabel, 74, 49);

        this.getContainer().addSyncCallback("SYNC_CURRENT_COPIES", sync ->
        {
            guiDecreaseButton.setDisabled(getTile().getCurrentCopiesNumber() <= 1);
            copiesLabel.setText(String.valueOf(getTile().getCurrentCopiesNumber()));
        });

        this.getContainer().addSyncCallback("SYNC_BUILD_STATUS", sync ->
        {
            guiBuildButton.setSelected(getTile().isBuildStatus());
            buildButtonLabel.setText(getTile().isBuildStatus() ? "Cancel" : "Synthetize");
        });

        this.getContainer().addSyncCallback("SYNC_BUILD_PROGRESS", sync -> {
            progressPane.setSize(54 * getTile().getBuildProgress(), progressPane.getHeight());
        });

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
