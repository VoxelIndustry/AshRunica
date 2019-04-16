package net.vi.ashrunica.common.rune;

import java.util.HashMap;
import java.util.Map;

public class RunePatterns
{
    private static RunePatterns instance;

    public static RunePatterns getInstance()
    {
        if (instance == null)
            instance = new RunePatterns();
        return instance;
    }

    private Map<String, RunePattern> patterns;

    public final RunePattern biSides;
    public final RunePattern triangleSides;
    public final RunePattern quadSides;
    public final RunePattern pentaSides;

    private RunePatterns()
    {
        this.patterns = new HashMap<>();

        this.biSides = new RunePattern(2, 2, 2, "2-mirror");
        this.triangleSides = new RunePattern(3, 3, 3, "3-triangle");
        this.quadSides = new RunePattern(4, 4, 4, "4-quad");
        this.pentaSides = new RunePattern(5, 5, 5, "5-penta");

        this.patterns.put(biSides.getName(), biSides);
        this.patterns.put(triangleSides.getName(), triangleSides);
        this.patterns.put(quadSides.getName(), quadSides);
        this.patterns.put(pentaSides.getName(), pentaSides);
    }

    public RunePattern fromName(String name)
    {
        return patterns.get(name);
    }
}
