package main.java.util;

import main.java.app.Randomiser;
import main.java.exception.TooManyItemsException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides various static methods to convert and format lists into strings and vice versa.
 */
public class Format {
    /**
     * Converts a list to a string, with the elements separated by a given string.
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
        Objects.requireNonNull(stringToSeparateBy, "stringToSeparateBy cannot be null.");

        final StringBuilder b = new StringBuilder();

        final Iterator it = list.iterator();
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
     * Splits a string into a list, with each new line being an element in the list.
     *
     * The maximum length of the list can be set, with an exception thrown if the
     * string contains too many items.
     *
     * @param string The {@code String} to split.
     * @param maxLength The maximum length of the list. Set this to 0 to remove the limit.
     *
     * @return {@code string} separated into a {@code List}.
     *
     * @throws TooManyItemsException if {@code string} contains more newline characters than {@code maxLength}.
     */
    public static List<String> splitStringToList(String string, int maxLength){
        Objects.requireNonNull(string, "string cannot be null.");

        long numOfLines = string.chars().filter(ch -> ch == '\n').count() + 1;
        Stream<String> st = string.lines();

        if(maxLength <= 0 || numOfLines <= maxLength){
            return st.collect(Collectors.toList());
        }else{
            throw new TooManyItemsException();
        }
    }

    /**
     * Formats a list of integers and returns it as a string.
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

        final List<String> formattedList = new ArrayList<>();

        for(int n : list){
            formattedList.add(Randomiser.NUMBER_FORMAT.format(n));
        }

        return convertListToString(formattedList, stringToSeparateBy);
    }

    /**
     * Concatenates a list of integers to a percentage.
     *
     * As an example, a list containing 5 and 6 becomes "56%".
     * The option can be selected to consider a list containing two zeros as "100%".
     *
     * @param nums The {@code Integer} {@code List} to concatenate.
     * @param doubleZeroIsOneHundred Whether two zeros should be considered "100%".
     *
     * @return A {@code String} representing the list as a percentage.
     */
    public static String integerListAsPercentage(List<Integer> nums, boolean doubleZeroIsOneHundred){
        final String percentage = convertListToString(nums, "");

        if(doubleZeroIsOneHundred && percentage.equals("00")){
            return "1" + percentage + "%";
        }else{
            return percentage + "%";
        }
    }
}
