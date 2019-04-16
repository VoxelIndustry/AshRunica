package net.vi.ashrunica.common.rune.complex;

import lombok.Value;
import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.common.rune.RuneSlot;

import java.util.EnumSet;

@Value
public class RuneComplexType
{
    private final String name;

    private final EnumSet<RuneSlot> compatibleSlots;

    private RuneComplexType(String name, EnumSet<RuneSlot> compatibleSlots)
    {
        this.name = name;
        this.compatibleSlots = compatibleSlots;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public NBTTagCompound toNBT(NBTTagCompound tag)
    {
        tag.setString("name", this.name);
        tag.setInteger("slots", this.compatibleSlots.size());

        int index = 0;
        for (RuneSlot slot : compatibleSlots)
        {
            tag.setString("slot" + index, slot.name());
            index++;
        }
        return tag;
    }

    public static class Builder
    {
        private String name;

        private EnumSet<RuneSlot> compatibleSlots = EnumSet.noneOf(RuneSlot.class);

        public Builder name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder slot(RuneSlot slot)
        {
            this.compatibleSlots.add(slot);
            return this;
        }

        public RuneComplexType create()
        {
            return new RuneComplexType(name, compatibleSlots);
        }
    }
}
