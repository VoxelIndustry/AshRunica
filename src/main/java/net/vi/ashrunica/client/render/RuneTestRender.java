package net.vi.ashrunica.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.tile.TileRuneTest;
import net.voxelindustry.brokkgui.animation.Interpolators;
import net.voxelindustry.brokkgui.paint.Color;
import net.voxelindustry.brokkgui.paint.Color1DGradient;

public class RuneTestRender extends TileEntitySpecialRenderer<TileRuneTest>
{
    private final ResourceLocation glyphAtlas = new ResourceLocation(AshRunica.MODID, "textures/runes/runes_atlas.png");
    private final ResourceLocation ring48     = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_48.png");
    private final ResourceLocation ringInt48  = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_int_48.png");
    private final ResourceLocation ring12     = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_12.png");
    private final ResourceLocation ring8      = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_8.png");

    private final Color1DGradient glyphGradient = Color1DGradient.build()
            .color(Color.RED, 0).color(Color.YELLOW, 0.5f).color(Color.RED, 1)
            .precompute(1 / 36f).create();

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

        int pass = MinecraftForgeClient.getRenderPass();

        if (pass == 0)
        {
            this.bindTexture(ring12);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -12, -12, 24, 24, 0, 0, 0, 1, 1);

            this.bindTexture(glyphAtlas);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -6, -6, 12, 12, 0, 16 / 24f, 0, 17 / 24f, 1);

            GlStateManager.rotate(modulo + 1 * partialTicks, 0, 0, 1);

            for (int i = 0; i < 6; i++)
                RenderHelper.drawTexRect(Tessellator.getInstance(), -1.5f, -17 - 4.5f * i, 4, 4, 0,
                        i * (1 / 24f), 0, (i + 1) * (1 / 24f), 1);

            for (int i = 0; i < 6; i++)
                RenderHelper.drawTexRect(Tessellator.getInstance(), -1.5f, 13 + 4.5f * i, 4, 4, 0,
                        (i + 8) * (1 / 24f), 0, (i + 9) * (1 / 24f), 1);

            this.bindTexture(ring48);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -48, -48, 96, 96, 0, 0, 0, 1, 1);

            RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, 11, 1, 33, 0, Color.BLACK);
            RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, 11, 1, 33, 0, Color.BLACK);

            RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, -11 - 33, 1, 33, 0, Color.BLACK);
            RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, -11 - 33, 1, 33, 0, Color.BLACK);
            GlStateManager.color(1, 1, 1, 1);

            this.bindTexture(ring8);

            GlStateManager.pushMatrix();
            GlStateManager.translate(-20, -20, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -8, -8, 16, 16, 4, 0, 0, 1, 1);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(20, 20, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -8, -8, 16, 16, 4, 0, 0, 1, 1);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, -44, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -8, -8, 16, 16, -4, 0, 0, 1, 1);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 44, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -8, -8, 16, 16, -4, 0, 0, 1, 1);
            GlStateManager.popMatrix();

            this.bindTexture(glyphAtlas);
            GlStateManager.disableLighting();

            float worldDelta = (float) (te.getWorld().getTotalWorldTime() % 100) / 100f;
            for (int angle = 0; angle < 360; angle += 10)
            {
                int i = angle / 10;

                float delta = Interpolators.QUAD_BOTH.apply(((i + (worldDelta * 36)) % 36) / 36f);
                int brightness = wrap(delta);
                Color color = glyphGradient.getValue(delta);

                RenderHelper.drawTexRectWithColorAndBrightness(Tessellator.getInstance(), -1.5f, -44.25f, 3.5f, 3.5f,
                        -0.01f, i * (1 / 24f), 0, (i + 1) * (1 / 24f), 1, color, brightness);
                GlStateManager.rotate(10, 0, 0, 1);
            }
            GlStateManager.enableLighting();
        }
        else
        {
            GlStateManager.rotate(modulo + 1 * partialTicks, 0, 0, 1);

            this.bindTexture(ringInt48);
            RenderHelper.enableAlpha();
            RenderHelper.drawTexRect(Tessellator.getInstance(), -44, -44, 88, 88, 0, 0, 0, 1, 1);
        }

        GlStateManager.popMatrix();
    }

    public int wrap(float delta)
    {
        if (delta < 0.5f)
            return (int) (100 + 140 * delta * 2);
        return (int) (240 - 140 * (delta - 0.5f) * 2);
    }
}
