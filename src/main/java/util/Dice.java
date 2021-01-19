package main.java.util;

/**
 * Represents a dice with a given number of sides.
 */
public enum Dice {
    D4(4, "4-sided"),
    D6(6, "6-sided"),
    D8(8, "8-sided"),
    D10(10, "10-sided"),
    D12(12, "12-sided"),
    D20(20, "20-sided");

    private final int sides;
    private final String name;

    Dice(int sides, String name){
        this.sides = sides;
        this.name = name;
    }

    /**
     * Returns the number of sides the dice has.
     *
     * @return The number of sides the dice has.
     */
    public int getSides() {
        return sides;
    }

    /**
     * Returns the name of the dice.
     *
     * @return The name of the dice.
     */
    @Override
    public String toString(){
        return name;
    }
}
