package net.vi.ashrunica.common.rune.complex.impl;

import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.common.rune.complex.RuneComplexData;
import net.vi.ashrunica.common.rune.complex.RuneComplexType;
import net.vi.ashrunica.common.rune.complex.RuneComplexes;

public class InventoryComplex extends RuneComplexData
{
    @Override
    public RuneComplexType getType()
    {
        return RuneComplexes.getInstance().inventory;
    }

    @Override
    public RuneComplexData fromNBT(NBTTagCompound tag)
    {
        return this;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound tag)
    {
        this.getType().toNBT(tag);
        return tag;
    }
}
