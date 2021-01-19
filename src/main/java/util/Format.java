package main.java.util;

import java.text.NumberFormat;
import java.util.*;

/**
 * Provides various static methods to convert and format lists into strings.
 */
public class Format {
    private static final Locale locale = Locale.getDefault();

    /**
     * Converts a List to a string, with the elements separated by a given string.
     *
     * @param list The {@code List} to convert.
     * @param stringToSeparateBy The {@code String} to separate each element by.
     *
     * @return A single {@code String} of the elements in the list.
     *
     * @throws NullPointerException if {@code list} is null.
     * @throws NullPointerException if {@code stringToSeparateBy} is null.
     */
    public static String convertListToString(List list, String stringToSeparateBy){
        Objects.requireNonNull(list, "list cannot be null.");
        Objects.requireNonNull(stringToSeparateBy, "stringToSeparateBy cannot be null");

        StringBuilder b = new StringBuilder();

        Iterator it = list.iterator();
        while(it.hasNext()){
            Object o = it.next();
            b.append(o.toString());

            if(it.hasNext()){
                b.append(stringToSeparateBy);
            }
        }

        return b.toString();
    }

    /**
     * Formats a list  of integers and returns it as a string.
     *
     * The integers are formatted according to locale and converted to a single string,
     * with each integer on a new line.
     *
     * More specifically, this method converts the list to a list of formatted strings,
     * then calls the method {@code convertListToString} on the converted list.
     *
     * @param list The {@code List} to format.
     * @param stringToSeparateBy The {@code String} to separate each integer by.
     *
     * @return All items of the list, formatted into a single {@code String}.
     *
     * @throws NullPointerException if {@code list} is null.
     */
    public static String integerListAsString(List<Integer> list, String stringToSeparateBy){
        Objects.requireNonNull(list, "List cannot be null.");

        List<String> formattedList = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getInstance(locale);

        for(int n : list){
            formattedList.add(formatter.format(n));
        }

        return convertListToString(formattedList, stringToSeparateBy);
    }

    /**
     * Concatenates a list of integers to a percentage.
     *
     * As an example, a list containing 5 and 6 becomes "56%".
     * A list containing two zeros becomes "100%".
     *
     * @param nums The {@code Integer} {@code List} to concatenate.
     *
     * @return A {@code String} representing the list as a percentage.
     */
    public static String integerListAsPercentage(List<Integer> nums){
        final String percentage = convertListToString(nums, "");

        if(percentage.equals("00")){
            return "1" + percentage + "%";
        }else{
            return percentage + "%";
        }
    }
}
