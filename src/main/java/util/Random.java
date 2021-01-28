package main.java.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides static methods for generating lists of pseudorandom integers,
 * and selecting random items from a list.
 */
public class Random {
    /**
     * Generates a list of non-unique, pseudorandom integers within a given range.
     * <br>
     * This method may return duplicate numbers.
     *
     * @param lowerBound The lower-bound number of the range (inclusive).
     * @param upperBound The upper-bound number of the range (inclusive).
     * @param quantity The quantity of integers to generate.
     *
     * @return An {@code Integer} {@code List} of non-unique pseudorandom numbers within the given range.
     */
    public static List<Integer> getRandomIntegerList(int lowerBound, int upperBound, int quantity){
        final List<Integer> randNums = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            SecureRandom rand = new SecureRandom();
            int num = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            randNums.add(num);
        }

        return randNums;
    }

    /**
     * Generates a list of unique, pseudorandom integers within a given range.
     * <br>
     * This method will not return duplicate numbers.
     *
     * @param lowerBound The lower-bound number of the range (inclusive).
     * @param upperBound The upper-bound number of the range (inclusive).
     * @param quantity The quantity of integers to generate.
     *
     * @return An {@code Integer} {@code List} of unique pseudorandom numbers within the given range.
     */
    public static List<Integer> getUniqueRandomIntegerList(int lowerBound, int upperBound, int quantity){
        final List<Integer> possibleNums = getAllIntegersInRange(lowerBound, upperBound);
        Collections.shuffle(possibleNums);

        final List<Integer> randNums = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            randNums.add(possibleNums.get(i));
        }

        return randNums;
    }

    /**
     * Builds a list of all integers between (and including) two numbers.
     *
     * @param lowerBound The lower-bound number of the range (inclusive).
     * @param upperBound The upper-bound number of the range (inclusive).
     *
     * @return An {@code Integer} {@code List} containing all numbers in the range.
     */
    private static List<Integer> getAllIntegersInRange(int lowerBound, int upperBound){
        List<Integer> nums = new ArrayList<>();

        for(int i = lowerBound; i <= upperBound; i++){
            nums.add(i);
        }

        return nums;
    }

    /**
     * Selects a number of non-unique, random items from a given list.
     * <br>
     * This method may return duplicate items.
     *
     * @param list The {@code List} to select items from.
     * @param quantity The number of items to select.
     *
     * @return A {@code List} containing randomly selected, non-unique items from {@code list}.
     */
    public static List<String> getRandItemsFromList(List<String> list, int quantity){
        final List<String> selectedItems = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            Collections.shuffle(list);

            String item = list.get(0);
            selectedItems.add(item);
        }

        return selectedItems;
    }

    /**
     * Selects a number of unique, random items from a given list.
     * <br>
     * This method will not return duplicate items.
     *
     * @param list The {@code List} to select items from.
     * @param quantity The number of items to select.
     *
     * @return A {@code List} containing randomly selected, unique items from {@code list}.
     */
    public static List<String> getUniqueRandItemsFromList(List<String> list, int quantity){
        Collections.shuffle(list);

        final List<String> selectedItems = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            String item = list.get(i);
            selectedItems.add(item);
        }

        return selectedItems;
    }
}
