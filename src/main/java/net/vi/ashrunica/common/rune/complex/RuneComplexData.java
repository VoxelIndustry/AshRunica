package net.vi.ashrunica.common.rune.complex;

import net.minecraft.nbt.NBTTagCompound;

public abstract class RuneComplexData
{
    public abstract RuneComplexType getType();

    public abstract RuneComplexData fromNBT(NBTTagCompound tag);

    public NBTTagCompound toNBT(NBTTagCompound tag)
    {
        getType().toNBT(tag);

        return tag;
    }
}
