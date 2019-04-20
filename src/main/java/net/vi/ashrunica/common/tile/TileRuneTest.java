package net.vi.ashrunica.common.tile;

import net.voxelindustry.steamlayer.tile.TileBase;

public class TileRuneTest extends TileBase
{
    public TileRuneTest()
    {
        
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        return pass == 0 || pass == 1;
    }
}
