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

package main.java.exception;

import main.java.app.Randomiser;

import javax.swing.JOptionPane;

/**
 * Provides a {@code RuntimeException} that displays an error message to the
 * user when they have entered more items than allowed in the list randomiser
 * or random item tabs.
 */
public class TooManyItemsException extends RuntimeException{
    /**
     * Provides the error message to display.
     */
    private static final String MESSAGE = "Number of items may not exceed "
            + Randomiser.NUMBER_FORMAT.format(Randomiser.MAX_LIST_ITEMS)
            + ".";

    public TooManyItemsException(){
        super();

        JOptionPane.showMessageDialog(null, MESSAGE,
                Randomiser.TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
