package net.vi.ashrunica.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.voxelindustry.brokkgui.paint.Color;
import org.lwjgl.opengl.GL11;

public class RenderHelper
{
    public static void drawColoredCircle(Tessellator tessellator, float centerX, float centerY, float radius,
                                         float zLevel, Color color)
    {
        float x = radius, y = 0;
        float P = 1 - radius;

        drawColoredRect(tessellator, centerX + radius, centerY, 1, 1, zLevel, color);
        drawColoredRect(tessellator, centerX - radius, centerY, 1, 1, zLevel, color);
        drawColoredRect(tessellator, centerX, centerY + radius, 1, 1, zLevel, color);
        drawColoredRect(tessellator, centerX, centerY - radius, 1, 1, zLevel, color);

        while (x > y)
        {
            y++;
            if (P <= 0)
                P = P + 2 * y + 1;
            else
            {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            if (x < y)
                break;

            drawColoredRect(tessellator, -x + centerX, -y + centerY, 1, 1, zLevel, color);
            drawColoredRect(tessellator, x + centerX, -y + centerY, 1, 1, zLevel, color);
            drawColoredRect(tessellator, -x + centerX, y + centerY, 1, 1, zLevel, color);
            drawColoredRect(tessellator, x + centerX, y + centerY, 1, 1, zLevel, color);
            if (x != y)
            {
                drawColoredRect(tessellator, -y + centerX, -x + centerY, 1, 1, zLevel, color);
                drawColoredRect(tessellator, y + centerX, -x + centerY, 1, 1, zLevel, color);
                drawColoredRect(tessellator, -y + centerX, x + centerY, 1, 1, zLevel, color);
                drawColoredRect(tessellator, y + centerX, x + centerY, 1, 1, zLevel, color);
            }
        }
    }

    public static void drawTexRect(Tessellator tessellator, float xStart, float yStart, float width,
                                   float height, float zLevel, float uMin, float vMin, float uMax, float vMax)
    {
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        tessellator.getBuffer().pos(xStart, yStart + height, zLevel).tex(uMin, vMax).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart + height, zLevel).tex(uMax, vMax).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart, zLevel).tex(uMax, vMin).endVertex();
        tessellator.getBuffer().pos(xStart, yStart, zLevel).tex(uMin, vMin).endVertex();
        tessellator.draw();
    }

    public static void drawTexRectWithColor(Tessellator tessellator, float xStart, float yStart, float width,
                                            float height, float zLevel, float uMin, float vMin, float uMax,
                                            float vMax, Color color)
    {
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        tessellator.getBuffer().pos(xStart, yStart + height, zLevel).tex(uMin, vMax)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart + height, zLevel).tex(uMax, vMax)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart, zLevel).tex(uMax, vMin)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart, yStart, zLevel).tex(uMin, vMin)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
    }

    public static void drawTexRectWithColorAndBrightness(Tessellator tessellator, float xStart, float yStart,
                                                         float width, float height, float zLevel, float uMin,
                                                         float vMin, float uMax, float vMax, Color color,
                                                         int brightness)
    {
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
        tessellator.getBuffer().pos(xStart, yStart + height, zLevel).tex(uMin, vMax)
                .lightmap(brightness, brightness)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart + height, zLevel).tex(uMax, vMax)
                .lightmap(brightness, brightness)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart + width, yStart, zLevel).tex(uMax, vMin)
                .lightmap(brightness, brightness)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.getBuffer().pos(xStart, yStart, zLevel).tex(uMin, vMin)
                .lightmap(brightness, brightness)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
    }

    public static void drawColoredRect(Tessellator tessellator, float startX, float startY,
                                       float width, float height, float zLevel, Color color)
    {
        boolean alpha = color.getAlpha() != 1;

        if (alpha)
            enableAlpha();

        GlStateManager.disableTexture2D();
        GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        tessellator.getBuffer().pos(startX, startY, zLevel).endVertex();
        tessellator.getBuffer().pos(startX, startY + height, zLevel).endVertex();
        tessellator.getBuffer().pos(startX + width, startY + height, zLevel).endVertex();
        tessellator.getBuffer().pos(startX + width, startY, zLevel).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();

        if (alpha)
            disableAlpha();
    }

    public static void enableAlpha()
    {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.depthMask(false);

    }

    public static void disableAlpha()
    {
        GlStateManager.disableAlpha();
        //    GlStateManager.disableBlend();
    }
}
