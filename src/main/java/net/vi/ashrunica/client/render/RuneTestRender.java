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
    private final ResourceLocation glyphAtlas = new ResourceLocation(AshRunica.MODID, "textures/runes/runes_atlas.png");
    private final ResourceLocation ring48     = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_48.png");
    private final ResourceLocation ring12     = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_12.png");
    private final ResourceLocation ring8      = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_8.png");

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
        GlStateManager.scale(0.0625f / 4, 0.0625f / 4, 0.0625f / 4);

        this.bindTexture(ring12);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -12, -12, 24, 24, 0, 0, 0, 1, 1);

        this.bindTexture(glyphAtlas);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -6, -6, 12, 12, 0, 16 / 24f, 0, 17 / 24f, 1);

        GlStateManager.rotate(modulo + 1 * partialTicks, 0, 0, 1);

        for (int i = 0; i < 7; i++)
            RenderHelper.drawTexturedRect(Tessellator.getInstance(), -1.5f, -17 - 4.5f * i, 4, 4, 0,
                    i * (1 / 24f), 0, (i + 1) * (1 / 24f), 1);

        for (int i = 0; i < 7; i++)
            RenderHelper.drawTexturedRect(Tessellator.getInstance(), -1.5f, 13 + 4.5f * i, 4, 4, 0,
                    (i + 8) * (1 / 24f), 0, (i + 9) * (1 / 24f), 1);

        this.bindTexture(ring48);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -48, -48, 96, 96, 0, 0, 0, 1, 1);

        RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, 11, 1, 33, 0, Color.BLACK);
        RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, 11, 1, 33, 0, Color.BLACK);

        RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, -11 - 33, 1, 33, 0, Color.BLACK);
        RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, -11 - 33, 1, 33, 0, Color.BLACK);
        GlStateManager.color(1, 1, 1, 1);

        this.bindTexture(ring8);

        GlStateManager.pushMatrix();
        GlStateManager.translate(-20, -20, 0);
        GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -8, -8, 16, 16, 4, 0, 0, 1, 1);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(20, 20, 0);
        GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -8, -8, 16, 16, 4, 0, 0, 1, 1);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, -44, 0);
        GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -8, -8, 16, 16, -4, 0, 0, 1, 1);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 44, 0);
        GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
        RenderHelper.drawTexturedRect(Tessellator.getInstance(), -8, -8, 16, 16, -4, 0, 0, 1, 1);
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}
