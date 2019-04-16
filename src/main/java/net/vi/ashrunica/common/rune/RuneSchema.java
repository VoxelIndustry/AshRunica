package net.vi.ashrunica.common.rune;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.common.rune.complex.RuneComplexData;
import net.vi.ashrunica.common.rune.complex.RuneComplexes;

import java.util.List;

public class RuneSchema
{
    private RunePattern                         pattern;
    private Multimap<RuneSlot, RuneComplexData> complexes;

    public RuneSchema(NBTTagCompound tag)
    {
        this.pattern = RunePatterns.getInstance().fromName(tag.getString("pattern"));

        this.complexes = MultimapBuilder.enumKeys(RuneSlot.class).arrayListValues().build();
        for (RuneSlot slot : RuneSlot.values())
        {
            int size = tag.getInteger(slot.name());

            if (size == 0)
                continue;

            for (int index = 0; index < size; index++)
                this.complexes.put(slot,
                        RuneComplexes.getInstance().dataFromNBT(tag.getCompoundTag(slot.name() + index)));
        }
    }

    private RuneSchema(RunePattern pattern, Multimap<RuneSlot, RuneComplexData> complexes)
    {
        this.pattern = pattern;
        this.complexes = complexes;
    }

    public static Builder build()
    {
        return new Builder();
    }

    public NBTTagCompound toNBT(NBTTagCompound tag)
    {
        tag.setString("pattern", this.pattern.getName());

        for (RuneSlot slot : RuneSlot.values())
        {
            if (!complexes.containsKey(slot))
            {
                tag.setInteger(slot.name(), 0);
                continue;
            }

            int size = complexes.get(slot).size();
            tag.setInteger(slot.name(), size);

            for (int index = 0; index < size; index++)
                tag.setTag(slot.name() + index,
                        ((List<RuneComplexData>) complexes.get(slot)).get(index).toNBT(new NBTTagCompound()));
        }

        return tag;
    }

    public static class Builder
    {
        private RunePattern                         pattern;
        private Multimap<RuneSlot, RuneComplexData> complexes;

        public Builder()
        {
            this.complexes = MultimapBuilder.enumKeys(RuneSlot.class).arrayListValues().build();
        }

        public Builder pattern(RunePattern pattern)
        {
            this.pattern = pattern;
            return this;
        }

        public Builder complex(RuneSlot slot, RuneComplexData complex)
        {
            this.complexes.put(slot, complex);
            return this;
        }

        public RuneSchema create()
        {
            return new RuneSchema(pattern, complexes);
        }
    }
}
