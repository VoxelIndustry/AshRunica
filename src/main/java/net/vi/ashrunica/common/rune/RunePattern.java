package net.vi.ashrunica.common.rune;

import lombok.Value;

@Value
public class RunePattern
{
    private final int interiorComplexCount;
    private final int peripheralComplexCount;
    private final int exteriorComplexCount;

    private final String name;
}
