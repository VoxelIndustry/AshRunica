package net.vi.ashrunica.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.client.render.RuneTestRender;
import net.vi.ashrunica.common.CommonProxy;
import net.vi.ashrunica.common.init.ARBlocks;
import net.vi.ashrunica.common.init.ARItems;
import net.vi.ashrunica.common.tile.TileRuneTest;
import net.voxelindustry.brokkgui.style.StylesheetManager;
import net.voxelindustry.steamlayer.common.model.IItemModelProvider;
import net.voxelindustry.steamlayer.common.model.IModelProvider;

import java.util.function.BiConsumer;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);

        OBJLoader.INSTANCE.addDomain(AshRunica.MODID);
        MinecraftForge.EVENT_BUS.register(this);

        ARItems.ITEMS.stream().filter(IItemModelProvider.class::isInstance)
                .filter(item -> ((IItemModelProvider) item).hasSpecialModel())
                .forEach(item -> ((IItemModelProvider) item).registerVariants());

        ClientRegistry.bindTileEntitySpecialRenderer(TileRuneTest.class, new RuneTestRender());
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);

        StylesheetManager.getInstance().addUserAgent(AshRunica.MODID, "/assets/" + AshRunica.MODID +
                "/css/theme.css");
    }

    @Override
    public void registerItemRenderer(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent e)
    {
        ARBlocks.BLOCKS.keySet().stream().filter(IModelProvider.class::isInstance).forEach(block ->
        {
            IModelProvider modelProvider = (IModelProvider) block;

            BiConsumer<Integer, Block> modelRegister = modelProvider.registerItemModels();
            for (int i = 0; i < modelProvider.getItemModelCount(); i++)
                modelRegister.accept(i, block);
        });
    }

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent e)
    {
        for (Item item : ARItems.ITEMS)
        {
            if (item instanceof IItemModelProvider && ((IItemModelProvider) item).hasSpecialModel())
                ((IItemModelProvider) item).registerModels();
            else
                AshRunica.proxy.registerItemRenderer(item, 0);
        }
    }
}
