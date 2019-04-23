package net.vi.ashrunica.client.render;

import com.google.common.base.Stopwatch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.vi.ashrunica.AshRunica;
import net.vi.ashrunica.common.rune.complex.RuneComplexType;
import net.vi.ashrunica.common.rune.complex.RuneComplexes;
import net.vi.ashrunica.common.tile.TileRuneTest;
import net.voxelindustry.brokkgui.animation.Interpolators;
import net.voxelindustry.brokkgui.paint.Color;
import net.voxelindustry.brokkgui.paint.Color1DGradient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RuneTestRender extends TileEntitySpecialRenderer<TileRuneTest>
{
    private final ResourceLocation ring48    = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_48.png");
    private final ResourceLocation ringInt48 = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_int_48.png");
    private final ResourceLocation ringAtlas = new ResourceLocation(AshRunica.MODID, "textures/runes/ring_atlas.png");

    private final Color1DGradient glyphGradient = Color1DGradient.build()
            .color(Color.RED, 0).color(Color.YELLOW, 0.5f).color(Color.RED, 1)
            .precompute(1 / 36f).create();

    private final GlyphAtlas futharkGlyphs = new GlyphAtlas(
            new ResourceLocation(AshRunica.MODID, "textures/runes/runes_atlas.png"), 768, 32, 24, 32);
    private final GlyphAtlas complexGlyphs = new GlyphAtlas(
            new ResourceLocation(AshRunica.MODID, "textures/runes/complex/spell.png"), 32, 32, 1, 32);
    private final GlyphAtlas asciiGlyphs   = new GlyphAtlas(
            new ResourceLocation("textures/font/ascii.png"), 128, 128, 16, 8);

    private List<Integer> timings = new ArrayList<>();
    private Stopwatch     watch   = Stopwatch.createUnstarted();

    @Override
    public void render(TileRuneTest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);

        watch.start();

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
            float worldDelta = (float) (te.getWorld().getTotalWorldTime() % 100) / 100f;

            this.bindTexture(ringAtlas);
            this.drawBigComplex(0, 0, RuneComplexes.getInstance().teleport, worldDelta);

            GlStateManager.rotate(modulo + 1 * partialTicks, 0, 0, 1);

            this.bindTexture(futharkGlyphs.getRsl());
            for (int i = 0; i < 6; i++)
            {
                float glyphDelta6 = Interpolators.QUAD_BOTH.apply(((i + (worldDelta * 6)) % 6) / 6f);

                this.drawGlyph(0.5f, -13 - 4.5f * i, 4, i, futharkGlyphs,
                        glyphGradient.getValue(glyphDelta6), wrap(glyphDelta6), -0.01f);

                this.drawGlyph(0.5f, 13 + 4.5f * i, 4, i + 6, futharkGlyphs,
                        glyphGradient.getValue(glyphDelta6), wrap(glyphDelta6), -0.01f);
            }

            this.bindTexture(ring48);
            RenderHelper.drawTexRect(Tessellator.getInstance(), -48, -48, 96, 96, 0, 0, 0, 1, 1);

            RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, 11, 1, 33, 0, Color.BLACK);
            RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, 11, 1, 33, 0, Color.BLACK);

            RenderHelper.drawColoredRect(Tessellator.getInstance(), -3, -11 - 33, 1, 33, 0, Color.BLACK);
            RenderHelper.drawColoredRect(Tessellator.getInstance(), 3, -11 - 33, 1, 33, 0, Color.BLACK);
            GlStateManager.color(1, 1, 1, 1);

            this.bindTexture(ringAtlas);

            GlStateManager.pushMatrix();
            GlStateManager.translate(-20, -20, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            this.drawSmallComplex(0, 0, RuneComplexes.getInstance().cognition);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(20, 20, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            this.drawSmallComplex(0, 0, RuneComplexes.getInstance().inventory);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, -44, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            this.drawSmallComplex(0, 0, RuneComplexes.getInstance().cognition);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 44, 0);
            GlStateManager.rotate(-(modulo + 1 * partialTicks), 0, 0, 1);
            this.drawSmallComplex(0, 0, RuneComplexes.getInstance().cognition);
            GlStateManager.popMatrix();

            this.bindTexture(futharkGlyphs.getRsl());
            GlStateManager.disableLighting();

            for (int angle = 0; angle < 360; angle += 10)
            {
                int i = angle / 10;

                float glyphDelta36 = Interpolators.QUAD_BOTH.apply(((i + (worldDelta * 36)) % 36) / 36f);

                this.drawGlyph(0.5f, -42.75f, 3.5f, i, futharkGlyphs,
                        glyphGradient.getValue(glyphDelta36), wrap(glyphDelta36), -0.01f);
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

        timings.add((int) watch.elapsed(TimeUnit.NANOSECONDS));

        watch.reset();

        if (timings.size() >= 10_000)
        {
            System.out.println("Mean render time: " + timings.stream().mapToInt(Integer::intValue).average() + " ns");
            timings.clear();
        }
    }

    private static final float RING_ATLAS_WIDTH  = 26;
    private static final float RING_ATLAS_HEIGHT = 58;

    private void drawSmallComplex(int x, int y, RuneComplexType complex)
    {
        RenderHelper.drawTexRect(Tessellator.getInstance(), x - 8, y - 8, 16, 16, -4,
                0, 0, 16 / RING_ATLAS_WIDTH, 16 / RING_ATLAS_HEIGHT);

        RenderHelper.drawTexRectWithColor(Tessellator.getInstance(), x - 5, y - 5, 10, 10, -4,
                16 / RING_ATLAS_WIDTH, 0, 1, 10 / RING_ATLAS_HEIGHT, complex.getColor());
    }

    private void drawBigComplex(int x, int y, RuneComplexType complex, float worldDelta)
    {
        RenderHelper.drawTexRect(Tessellator.getInstance(), x - 12, y - 12, 24, 24, -4,
                0, 16 / RING_ATLAS_HEIGHT, 24 / RING_ATLAS_WIDTH, 40 / RING_ATLAS_HEIGHT);

        RenderHelper.drawTexRectWithColor(Tessellator.getInstance(), x - 9, y - 9, 18, 18, -4,
                0, 40 / RING_ATLAS_HEIGHT, 18 / RING_ATLAS_WIDTH, 1, complex.getColor());

        this.bindTexture(complexGlyphs.getRsl());
        float glyphDelta = Interpolators.QUAD_BOTH.apply(worldDelta);
        this.drawGlyph(x, y, 12, complex.getIconIndex(), complexGlyphs,
                glyphGradient.getValue(glyphDelta), wrap(glyphDelta), -4.01f);
    }

    private void drawGlyph(float x, float y, float size, int letterIndex, GlyphAtlas atlas, Color color,
                           int brightness, float zLevel)
    {
        float uMin = letterIndex % atlas.getRowLength() * atlas.getGlyphSize();
        float vMin = (float) (Math.floor(letterIndex / atlas.getRowLength()) * atlas.getGlyphSize());

        RenderHelper.drawTexRectWithColorAndBrightness(Tessellator.getInstance(), x - size / 2f, y - size / 2f,
                size, size, zLevel,
                uMin / atlas.getWidth(), vMin / atlas.getHeight(),
                (uMin + atlas.getGlyphSize()) / atlas.getWidth(),
                (vMin + atlas.getGlyphSize()) / atlas.getHeight(),
                color, brightness);
    }

    public int wrap(float delta)
    {
        if (delta < 0.5f)
            return (int) (100 + 140 * delta * 2);
        return (int) (240 - 140 * (delta - 0.5f) * 2);
    }
}
