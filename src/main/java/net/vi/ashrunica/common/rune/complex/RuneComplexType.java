package net.vi.ashrunica.common.rune.complex;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.common.rune.RuneSlot;
import net.voxelindustry.brokkgui.paint.Color;

import java.util.EnumSet;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RuneComplexType
{
    private final String name;
    private final Color  color;
    private final int    iconIndex;

    private final EnumSet<RuneSlot> compatibleSlots;

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
        private Color  color;
        private int    iconIndex;

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

        public Builder color(Color color)
        {
            this.color = color;
            return this;
        }

        public Builder iconIndex(int iconIndex)
        {
            this.iconIndex = iconIndex;
            return this;
        }

        public RuneComplexType create()
        {
            return new RuneComplexType(name, color, iconIndex, compatibleSlots);
        }
    }
}
