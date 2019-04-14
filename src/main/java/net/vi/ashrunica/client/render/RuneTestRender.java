package net.vi.ashrunica.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.tile.TileRuneTest;
import net.voxelindustry.brokkgui.paint.Color;

public class RuneTestRender extends TileEntitySpecialRenderer<TileRuneTest>
{
    @Override
    public void render(TileRuneTest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + .5f, y + .5f, z + .5f);

        GlStateManager.disableLighting();

        int li = te.getWorld().getCombinedLight(te.getPos(), 15728640);
        int i1 = li % 65536;
        int j1 = li / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i1, (float) j1);

        long modulo = te.getWorld().getTotalWorldTime() % 360;
        GlStateManager.rotate(modulo + 1 * partialTicks, 0, 0, 1);
        GlStateManager.scale(0.0625f / 4, 0.0625f / 4, 0.0625f / 4);

        // Ext ring
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 48, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 47, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 46, 0, Color.BLACK);

        // Main ring
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 12, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 11, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 0, 10, 0, Color.BLACK);

        // Int Ring 1
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 20, 20, 8, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 20, 20, 7, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 20, 20, 6, 0, Color.BLACK);

        // Int Ring 2
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), -20, -20, 8, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), -20, -20, 7, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), -20, -20, 6, 0, Color.BLACK);

        // Periph Ring 1
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 46, 8, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 46, 7, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, 46, 6, 0, Color.BLACK);

        // Periph Ring 2
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, -46, 8, 0, Color.BLACK);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, -46, 7, 0, Color.LIGHT_GRAY);
        RenderHelper.drawColoredCircle(Tessellator.getInstance(), 0, -46, 6, 0, Color.BLACK);

        RenderHelper.drawColoredRect(Tessellator.getInstance(), 12, -3, 34, 1, 0, Color.BLACK);
        RenderHelper.drawColoredRect(Tessellator.getInstance(), 12, 3, 34, 1, 0, Color.BLACK);

        RenderHelper.drawColoredRect(Tessellator.getInstance(), -12 - 34, -3, 34, 1, 0, Color.BLACK);
        RenderHelper.drawColoredRect(Tessellator.getInstance(), -12 - 34, 3, 34, 1, 0, Color.BLACK);

        this.bindTexture(new ResourceLocation(AshRunica.MODID, "textures/runes/runes_atlas.png"));

        GlStateManager.rotate(90, 0, 0, 1);

        for (int i = 0; i < 7; i++)
            RenderHelper.drawTexturedRect(Tessellator.getInstance(), -1.5f, -17 - 4.5f * i, 4, 4, 0,
                    i * (1 / 24f), 0, (i + 1) * (1 / 24f), 1);

        for (int i = 0; i < 7; i++)
            RenderHelper.drawTexturedRect(Tessellator.getInstance(), -1.5f, 13 + 4.5f * i, 4, 4, 0,
                    (i + 8) * (1 / 24f), 0, (i + 9) * (1 / 24f), 1);

        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -6, -6, 12, 12, 0, 16/24f, 0, 17 / 24f, 1);

        GlStateManager.popMatrix();
    }
}
