package net.vi.ashrunica;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.vi.ashrunica.common.CommonProxy;
import net.voxelindustry.brokkgui.BrokkGuiPlatform;
import net.voxelindustry.steamlayer.common.container.CustomCreativeTab;
import org.apache.logging.log4j.Logger;

@Mod(modid = AshRunica.MODID, name = AshRunica.NAME, version = AshRunica.VERSION)
public class AshRunica
{
    public static final String MODID   = "ashrunica";
    public static final String NAME    = "Ash Runica";
    public static final String VERSION = "0.1.0";

    @Mod.Instance(MODID)
    public static AshRunica instance;

    public static final CreativeTabs TAB_ALL = new CustomCreativeTab(MODID, () -> new ItemStack(Items.BEEF));

    public static Logger logger;

    @SidedProxy(clientSide = "net.vi.ashrunica.client.ClientProxy",
            serverSide = "net.vi.ashrunica.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);

        BrokkGuiPlatform.getInstance().enableRenderDebug(true);
    }
}
