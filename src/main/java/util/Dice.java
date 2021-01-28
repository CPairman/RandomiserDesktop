package main.java.util;

/**
 * Represents a die with a given number of sides.
 */
public enum Dice{
    /**
     * Represents a 4-sided die.
     */
    D4(4, "4-sided"),

    /**
     * Represents a 6-sided die.
     */
    D6(6, "6-sided"),

    /**
     * Represents an 8-sided die.
     */
    D8(8, "8-sided"),

    /**
     * Represents a 10-sided die.
     */
    D10(10, "10-sided"),

    /**
     * Represents a 12-sided die.
     */
    D12(12, "12-sided"),

    /**
     * Represents a 20-sided die.
     */
    D20(20, "20-sided");

    private final int sides;
    private final String name;

    Dice(int sides, String name){
        this.sides = sides;
        this.name = name;
    }

    /**
     * Returns the number of sides the die has.
     *
     * @return The number of sides the die has.
     */
    public int getSides() {
        return sides;
    }

    /**
     * Returns the name of the die.
     *
     * @return The name of the die.
     */
    @Override
    public String toString(){
        return name;
    }
}
