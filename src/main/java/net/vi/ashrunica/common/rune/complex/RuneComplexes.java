package net.vi.ashrunica.common.rune.complex;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.nbt.NBTTagCompound;
import net.vi.ashrunica.common.rune.RuneSlot;
import net.vi.ashrunica.common.rune.complex.impl.InventoryComplex;

import java.util.HashMap;
import java.util.Map;

public class RuneComplexes
{
    private static RuneComplexes instance;

    public static RuneComplexes getInstance()
    {
        if (instance == null)
            instance = new RuneComplexes();
        return instance;
    }

    private final Map<String, RuneComplexType>                    complexTypes;
    private final Table<RuneComplexType, String, RuneComplexData> complexesData;

    public final RuneComplexType elementSpell;
    public final RuneComplexType inventory;
    public final RuneComplexType memory;
    public final RuneComplexType cognition;
    public final RuneComplexType teleport;

    private RuneComplexes()
    {
        this.complexTypes = new HashMap<>();
        this.complexesData = HashBasedTable.create();

        this.elementSpell = RuneComplexType.builder().name("element-spell").slot(RuneSlot.MAIN).create();
        this.inventory = RuneComplexType.builder().name("element-inventory").slot(RuneSlot.SPACE).create();
        this.memory = RuneComplexType.builder().name("element-memory").slot(RuneSlot.SPACE).create();
        this.cognition = RuneComplexType.builder().name("element-cognition").slot(RuneSlot.SPACE).create();
        this.teleport = RuneComplexType.builder().name("element-teleport")
                .slot(RuneSlot.MAIN).slot(RuneSlot.INTERIOR).create();

        this.complexTypes.put(elementSpell.getName(), elementSpell);
        this.complexTypes.put(inventory.getName(), inventory);
        this.complexTypes.put(memory.getName(), memory);
        this.complexTypes.put(cognition.getName(), cognition);
        this.complexTypes.put(teleport.getName(), teleport);

        this.complexesData.put(inventory, "main", new InventoryComplex());
    }

    public RuneComplexType typeFromNBT(NBTTagCompound tag)
    {
        return complexTypes.get(tag.getString("name"));
    }

    public RuneComplexData dataFromNBT(NBTTagCompound tag)
    {
        return complexesData.get(this.typeFromNBT(tag), tag.getString("variant")).fromNBT(tag);
    }
}
