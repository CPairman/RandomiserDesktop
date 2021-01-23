package main.java.exception;

import main.java.app.Randomiser;

import javax.swing.JOptionPane;

/**
 * Provides a {@code RuntimeException} that displays an error message to the
 * user when they have entered more items than allowed in the list randomiser.
 */
public class TooManyItemsException extends RuntimeException{
    private static final String MESSAGE = "Number of items may not exceed "
            + Randomiser.NUMBER_FORMAT.format(Randomiser.MAX_LIST_ITEMS)
            + ".";

    public TooManyItemsException(){
        super();

        JOptionPane.showMessageDialog(null, MESSAGE,
                Randomiser.TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
