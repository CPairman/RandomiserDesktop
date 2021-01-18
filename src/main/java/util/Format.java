package main.java.util;

import java.text.NumberFormat;
import java.util.*;

/**
 * Provides various static methods to convert and format lists into strings.
 */
public class Format {
    private static final Locale locale = Locale.getDefault();

    /**
     * Converts a {@code List} to a {@code String}, with each element on a new line.
     *
     * @param list The {@code List} to convert.
     *
     * @return A {@code String} of the elements in the list.
     *
     * @throws NullPointerException if {@code list} is null.
     */
    public static String convertListToString(List list){
        Objects.requireNonNull(list, "List cannot be null.");

        StringBuilder b = new StringBuilder();

        for(Object o : list) {
            b.append(o.toString());
            b.append('\n');
        }

        return b.toString();
    }

    /**
     * Formats a {@code List}  of integers and returns it as a {@code String}.
     *
     * The integers are formatted according to locale and converted to a single string,
     * with each integer on a new line.
     *
     * @param list The {@code List} to format.
     *
     * @return All items of {@code list}, formatted into a {@code String}.
     *
     * @throws NullPointerException if {@code list} is null.
     */
    public static String formatIntegerListAsString(List<Integer> list){
        Objects.requireNonNull(list, "List cannot be null.");

        List<String> formattedList = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getInstance(locale);

        for(int n : list){
            formattedList.add(formatter.format(n));
        }

        return convertListToString(formattedList);
    }
}
