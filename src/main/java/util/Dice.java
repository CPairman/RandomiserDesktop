/*
 * Copyright (c) 2021. Calum Pairman.
 *
 * Randomiser (the "Software") is free for use in any environment, including
 * but not necessarily limited to: personal, academic, commercial, government,
 * business, non-profit, and for-profit. "Free" in the preceding sentence means
 * that there is no cost or charge associated with the installation and use of
 * the Software.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of the Software, to use the Software without restriction, including the
 * rights to use, copy, publish, and distribute the Software, and to permit
 * persons to whom the Software is furnished to do so.
 *
 * You may not modify, adapt, rent, lease, loan, sell, or create derivative
 * works based upon the Software or any part thereof.
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *
 */

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
