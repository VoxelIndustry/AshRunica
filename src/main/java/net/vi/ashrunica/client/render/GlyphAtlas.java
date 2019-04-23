package net.vi.ashrunica.client.render;

import lombok.Value;
import net.minecraft.util.ResourceLocation;

@Value
public class GlyphAtlas
{
    private final ResourceLocation rsl;

    private final int width;
    private final int height;

    private final int rowLength;
    private final int glyphSize;
}
