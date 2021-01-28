package main.java.app;

import main.java.util.Dice;
import main.java.util.Format;
import main.java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Provides handling methods for components on the main app window.
 */
public class Randomiser {
    /**
     * The window title for the main window, and all dialogs.
     */
    public static final String TITLE = "Randomiser";

    /**
     * The maximum number of items the user can enter into the
     * list randomiser or item picker.
     */
    public static final int MAX_LIST_ITEMS = 10_000;

    /**
     * Stores the system locale for use in formatting.
     */
    public static final Locale LOCALE = Locale.getDefault();

    /**
     * Provides an instance of {@code NumberFormat} to format
     * numbers according to the system locale.
     */
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(LOCALE);

    /**
     * The upper-bound limit on the random number generator.
     */
    private static final int RAND_NUM_MAXIMUM = 10_000_000;

    /**
     * The lower-bound limit on the random number generator.
     */
    private static final int RAND_NUM_MINIMUM = -10_000_000;

    /**
     * The maximum quantity that the user can request on any tab.
     *
     * <p>This is used on the random number generator, dice roller,
     * and item picker.
     */
    private static final int MAX_QUANTITY = 100;

    /**
     * The minimum quantity that the user can request on any tab.
     *
     * <p>This is used on the random number generator, dice roller,
     * and item picker.
     */
    private static final int MIN_QUANTITY = 1;

    /**
     * Defines a default/starting value  of 1 for a {@code JSpinner}.
     */
    private static final int DEFAULT_SPINNER_VALUE = 1;

    /**
     * Defines that each {@code JSpinner} should increment in
     * steps of 1.
     */
    private static final int DEFAULT_SPINNER_STEP = 1;

    /**
     * Text for the instruction {@code JLabel} on the list
     * randomiser and item picker tabs.
     */
    private static final String LIST_INPUT_INSTRUCTION_TEXT = "Enter up to "
            + NUMBER_FORMAT.format(MAX_LIST_ITEMS)
            + " items. Put each item on a new line:";

    /**
     * Provides text for a warning message when the user has
     * requested more unique numbers than exist between the
     * upper-bound and lower-bound values they've entered in
     * the random number generator.
     */
    private static final String RAND_NUM_QUANTITY_TOO_HIGH_MESSAGE = """
                                There are not enough unique numbers in this range.
                                All possible numbers will be generated.""";

    /**
     * Provides text for a warning message when the user has
     * requested more unique items from the item picker than
     * exist in the list of items the user has entered.
     */
    private static final String RAND_ITEM_QUANTITY_TOO_HIGH_MESSAGE = """
                                There are not enough items in the list.
                                All items will be chosen.""";

    /**
     * This is the top level {@code JPanel} of the frame.
     */
    private JPanel mainPanel;

    /**
     * Components of the random number generator tab.
     */
    private JSpinner randNumQuantity;
    private JSpinner randNumLowerBound;
    private JSpinner randNumUpperBound;
    private JCheckBox randNumAllowDuplicates;
    private JCheckBox randNumKeepPrevious;
    private JTextArea generatedNumsOutput;
    private JButton randNumClear;
    private JButton randNumGenerate;

    /**
     * Components of the dice roll tab.
     */
    private JSpinner diceQuantity;
    private JComboBox<Dice> diceType;
    private JCheckBox rollForPercentage;
    private JCheckBox keepPreviousRolls;
    private JTextArea diceRollOutput;
    private JButton rollDiceButton;
    private JButton diceRollClear;

    /**
     * Components of the list randomiser tab.
     */
    private JLabel listRandomiserInstruction;
    private JTextArea randomiseListInput;
    private JTextArea randomiseListOutput;
    private JButton randListInputClear;
    private JButton randomiseListButton;
    private JButton randListOutputClear;

    /**
     * Components of the item picker tab.
     */
    private JSpinner randItemQuantity;
    private JCheckBox randItemAllowRepeats;
    private JCheckBox randItemKeepPrevious;
    private JLabel randItemInstruction;
    private JTextArea randItemInput;
    private JButton randItemInputClear;
    private JButton randItemSelect;
    private JTextArea randItemOutput;
    private JButton randItemOutputClear;

    /**
     * Returns the top level JPanel of the frame.
     *
     * @return The top level {@code JPanel}.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Initialises components of the UI, where initialisation code cannot be generated by the IDE.
     * <p>This method is called by the class constructor generated by the IDE.
     */
    private void createUIComponents() {
        createRandNumComponents();
        createDiceRollComponents();
        createListRandomiserComponents();
        createRandomItemComponents();
    }

    /**
     * Initialises components of the random number generator tab.
     */
    private void createRandNumComponents(){
        randNumQuantity = new JSpinner(
                new SpinnerNumberModel(DEFAULT_SPINNER_VALUE,
                        MIN_QUANTITY, MAX_QUANTITY,
                        DEFAULT_SPINNER_STEP));

        randNumLowerBound = new JSpinner(
                new SpinnerNumberModel(
                        DEFAULT_SPINNER_VALUE,
                        RAND_NUM_MINIMUM,
                        RAND_NUM_MAXIMUM,
                        DEFAULT_SPINNER_STEP));

        randNumUpperBound = new JSpinner(
                new SpinnerNumberModel(
                        DEFAULT_SPINNER_VALUE,
                        RAND_NUM_MINIMUM,
                        RAND_NUM_MAXIMUM,
                        DEFAULT_SPINNER_STEP));

        randNumGenerate = new JButton();
        randNumGenerate.addActionListener(e -> generateRandomNumbers());

        randNumClear = new JButton();
        randNumClear.addActionListener(e -> clearGeneratedNumsOutput());
    }

    /**
     * Initialises components of the dice roll tab.
     */
    private void createDiceRollComponents(){
        diceQuantity = new JSpinner(
                new SpinnerNumberModel(DEFAULT_SPINNER_VALUE,
                        MIN_QUANTITY, MAX_QUANTITY,
                        DEFAULT_SPINNER_STEP));
        diceQuantity.addChangeListener(e -> toggleRollForPercentage());

        diceType = new JComboBox<>(new DefaultComboBoxModel<>(Dice.values()));
        diceType.setSelectedItem(Dice.D6);
        diceType.addActionListener(e -> toggleRollForPercentage());

        rollDiceButton = new JButton();
        rollDiceButton.addActionListener(e -> rollDice());

        diceRollClear = new JButton();
        diceRollClear.addActionListener(e -> clearDiceRollOutput());
    }

    /**
     * Initialises components of the list randomiser tab.
     */
    private void createListRandomiserComponents(){
        listRandomiserInstruction = new JLabel(LIST_INPUT_INSTRUCTION_TEXT);

        randListInputClear = new JButton();
        randListInputClear.addActionListener((e -> clearRandomiseListInput()));

        randomiseListButton = new JButton();
        randomiseListButton.addActionListener(e -> randomiseList());

        randListOutputClear = new JButton();
        randListOutputClear.addActionListener(e -> clearRandomiseListOutput());
    }

    /**
     * Initialises components of the random item tab.
     */
    private void createRandomItemComponents(){
        randItemQuantity = new JSpinner(
                new SpinnerNumberModel(DEFAULT_SPINNER_VALUE,
                        MIN_QUANTITY, MAX_QUANTITY,
                        DEFAULT_SPINNER_STEP));

        randItemInstruction = new JLabel(LIST_INPUT_INSTRUCTION_TEXT);

        randItemInputClear = new JButton();
        randItemInputClear.addActionListener(e -> clearRandomItemInput());

        randItemSelect = new JButton();
        randItemSelect.addActionListener(e -> selectRandomItems());

        randItemOutputClear = new JButton();
        randItemOutputClear.addActionListener(e -> clearRandomItemOutput());
    }

    /**
     * Sets the cursor to a 'wait' cursor while hovering over the main panel.
     */
    private void showWaitCursor(){
        mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    /**
     * Sets the cursor to the default while hovering over the main panel.
     */
    private void showDefaultCursor(){
        mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Handles the 'Generate' button being clicked on the random number generator tab.
     *
     * <p>If the checkbox to allow duplicate numbers is checked, a list of non-unique pseudorandom
     * numbers is generated within the range given by the user and displayed on the form.
     *
     * <p>If the checkbox is not checked, the method will generate a list of unique pseudorandom
     * numbers, within the range given by the user, and display this on the form.
     */
    private void generateRandomNumbers(){
        showWaitCursor();

        final int lowerBound = getLowerBound();
        final int upperBound = getUpperBound();

        final int quantity;
        final List<Integer> randNums;

        if(randNumAllowDuplicates.isSelected()){
            quantity = (int) randNumQuantity.getValue();
            randNums = Random.getRandomIntegerList(lowerBound, upperBound, quantity);
        }else{
            quantity = checkUniqueNumbersQuantity(lowerBound, upperBound);
            randNums = Random.getUniqueRandomIntegerList(lowerBound, upperBound, quantity);
        }

        displayGeneratedNums(randNums);
        showDefaultCursor();
    }

    /**
     * Returns the lower-bound value entered by the user on the random number generator tab.
     *
     * <p>It's possible for the user to enter upper- and lower-bound values in the wrong order,
     * i.e entering a smaller number in {@code randNumUpperBound} and a larger number in
     * {@code randNumLowerBound}.
     *
     * <p>This method accounts for that by returning the smallest number entered in either spinner.
     *
     * @return The lower-bound value entered by the user on the random number generator tab.
     */
    private int getLowerBound(){
        return Math.min((int) randNumLowerBound.getValue(),
                (int) randNumUpperBound.getValue());
    }

    /**
     * Returns the upper-bound value entered by the user on the random number generator tab.
     *
     * <p>It's possible for the user to enter upper- and lower-bound values in the wrong order,
     * i.e entering a smaller number in {@code randNumUpperBound} and a larger number in
     * {@code randNumLowerBound}.
     *
     * <p>This method accounts for that by returning the largest number entered in either spinner.
     *
     * @return The upper-bound value entered by the user on the random number generator tab.
     */
    private int getUpperBound(){
        return Math.max((int) randNumLowerBound.getValue(),
                (int) randNumUpperBound.getValue());
    }

    /**
     * Returns the quantity of numbers that the user has requested, only for use when the user
     * has requested UNIQUE numbers.
     *
     * <p>As the numbers must be unique, it's possible for the user to request more numbers than
     * exist between {@code lowerBound} and {@code upperBound}.
     *
     * <p>If this happens, the method will display a warning to the user, then return the maximum
     * quantity possible.
     *
     * @param lowerBound The lower-bound of the range requested by the user.
     * @param upperBound The upper-bound of the range requested by the user.
     *
     * @return The quantity of numbers that the user has requested, or the maximum quantity possible.
     */
    private int checkUniqueNumbersQuantity(int lowerBound, int upperBound){
        final int quantity = (int) randNumQuantity.getValue();
        final int range = upperBound - lowerBound + 1;

        if(quantity > range){
            JOptionPane.showMessageDialog(mainPanel,
                    RAND_NUM_QUANTITY_TOO_HIGH_MESSAGE, TITLE,
                    JOptionPane.WARNING_MESSAGE);

            return range;
        }else{
            return quantity;
        }
    }

    /**
     * Formats and displays a list of integers on the random number generator tab.
     *
     * @param nums The {@code List} of integers to display.
     */
    private void displayGeneratedNums(List<Integer> nums){
        final String numsAsString = Format.integerListAsString(nums, "\n");

        if(randNumKeepPrevious.isSelected()){
            generatedNumsOutput.append(numsAsString);
        }else{
            generatedNumsOutput.setText(numsAsString);
        }

        generatedNumsOutput.append("\n\n");
    }

    /**
     * Clears the output box for generated numbers.
     */
    private void clearGeneratedNumsOutput(){
        generatedNumsOutput.setText("");
    }

    /**
     * Toggles whether or not the "Roll for percentage" checkbox is enabled.
     */
    private void toggleRollForPercentage(){
        final Dice type = (Dice) diceType.getSelectedItem();
        final int quantity = (int) diceQuantity.getValue();

        if((type == Dice.D10)
                && (quantity == 2)){
            rollForPercentage.setEnabled(true);
        }else{
            rollForPercentage.setSelected(false);
            rollForPercentage.setEnabled(false);
        }
    }

    /**
     * Handles the 'Roll' button being clicked on the dice roll tab.
     *
     * <p>This method essentially uses the same logic as the random number generator,
     * where the upper-bound number is chosen by the user with {@code diceType}.
     */
    private void rollDice(){
        if(rollForPercentage.isSelected()){
            rollForPercentage();
        }else{
            final int quantity = (int) diceQuantity.getValue();
            regularRoll(quantity);
        }
    }

    /**
     * Simulates rolling two ten-sided dice with numbers 0-9.
     *
     * <p>The results are displayed on the dice roll tab, along with the sum of the dice, and the
     * results concatenated into a percentage.
     */
    private void rollForPercentage(){
        final List<Integer> results = Random.getRandomIntegerList(0, 9, 2);
        final int sum = sum(results);

        final String percentage = Format.integerListAsPercentage(results, true);

        displayDiceRoll(results, percentage, sum);
    }

    /**
     * Simulates rolling a given number of dice, with the number of sides on each dice given
     * by {@code diceType}.
     *
     * <p>The results are displayed on the dice roll tab along with the sum of the dice.
     *
     * @param quantity The number of dice to roll.
     */
    private void regularRoll(int quantity){
        final Dice dice = (Dice) diceType.getSelectedItem();
        final int sides = dice.getSides();

        final List<Integer> results = Random.getRandomIntegerList(1, sides, quantity);
        final int sum = sum(results);

        displayDiceRoll(results, sum);
        diceRollOutput.append("\n");
    }

    /**
     * Returns the sum of a list of integers.
     *
     * @param nums The {@code Integer} {@code List} to sum.
     *
     * @return The sum of all the elements in {@code nums}.
     */
    private int sum(List<Integer> nums){
        int sum = 0;

        for(int n : nums){
            sum += n;
        }

        return sum;
    }

    /**
     * Displays the results of a dice roll, along with the sum of the dice.
     *
     * @param dice The results of the dice roll.
     * @param sum The sum of the dice.
     */
    private void displayDiceRoll(List<Integer> dice, int sum){
        final String diceAsString = Format.integerListAsString(dice, ", ");

        if(keepPreviousRolls.isSelected()){
            diceRollOutput.append(diceAsString + "\n");
        }else{
            diceRollOutput.setText(diceAsString + "\n");
        }

        diceRollOutput.append("Total: " + sum + "\n");
    }

    /**
     * Displays the results of a dice roll, along with the sum of the dice, and the dice
     * concatenated into a percentage.
     *
     * @param dice The results of the dice roll.
     * @param sum The sum of the dice.
     */
    private void displayDiceRoll(List<Integer> dice, String percentage, int sum){
        displayDiceRoll(dice, sum);
        diceRollOutput.append("Percentage: " + percentage + "\n");
        diceRollOutput.append("\n");
    }

    /**
     * Clears the output box for dice rolls.
     */
    private void clearDiceRollOutput(){
        diceRollOutput.setText("");
    }

    /**
     * Handles the 'Randomise' button being clicked on the list randomiser tab.
     *
     * <p>The user's input is converted to a list and shuffled.
     *
     * <p>The shuffled list is then converted back to a string and displayed.
     */
    private void randomiseList(){
        final String input = randomiseListInput.getText();
        final List<String> shuffledList = getShuffledList(input);

        displayRandomisedList(shuffledList);
    }

    /**
     * Splits a string into a list and shuffles it.
     *
     * @param string The {@code String} to shuffle.
     *
     * @return {@code string} separated into a {@code List}.
     */
    private List<String> getShuffledList(String string){
        final List<String> inputList = Format.splitStringToList(string, MAX_LIST_ITEMS);

        Collections.shuffle(inputList);

        return inputList;
    }

    /**
     * Formats and displays a list on the list randomiser tab.
     *
     * @param list The {@code List} to display.
     */
    private void displayRandomisedList(List<String> list){
        final String listAsString = Format.convertListToString(list, "\n");

        randomiseListOutput.setText(listAsString);
    }

    /**
     * Clears the list randomiser input box.
     */
    private void clearRandomiseListInput(){
        randomiseListInput.setText("");
    }

    /**
     * Clears the list randomiser output box.
     */
    private void clearRandomiseListOutput(){
        randomiseListOutput.setText("");
    }

    /**
     * Handles the 'Select' button being clicked on the random item tab.
     *
     * <p>The user's input is converted to a list, then the number of items specified by
     * the user is selected and displayed.
     */
    private void selectRandomItems(){
        final String input = randItemInput.getText();
        final List<String> inputList = Format.splitStringToList(input, MAX_LIST_ITEMS);

        final int quantity;
        final List<String> selectedItems;

        if(randItemAllowRepeats.isSelected()){
            quantity = (int) randItemQuantity.getValue();
            selectedItems = Random.getRandItemsFromList(inputList, quantity);
        }else{
            quantity = checkUniqueItemsQuantity(inputList.size());
            selectedItems = Random.getUniqueRandItemsFromList(inputList, quantity);
        }

        displaySelectedItems(selectedItems);
    }

    /**
     * Returns the quantity of items that the user has requested, only for use when the user
     * has requested UNIQUE items.
     *
     * <p>As the items must be unique, it's possible for the user to request more items than exist
     * in their input list.
     *
     * <p>If this happens, the method will display a warning to the user, then return the size
     * of the list.
     *
     * @param listSize The size of the list.
     *
     * @return The quantity of items that the user has requested, or {@code listSize}.
     */
    private int checkUniqueItemsQuantity(int listSize){
        final int quantity = (int) randItemQuantity.getValue();

        if(quantity <= listSize){
            return quantity;
        }else{
            JOptionPane.showMessageDialog(mainPanel,
                    RAND_ITEM_QUANTITY_TOO_HIGH_MESSAGE,
                    TITLE, JOptionPane.WARNING_MESSAGE);

            return listSize;
        }
    }

    /**
     * Formats and displays a list of items on the random item tab.
     *
     * @param items The {@code List} of items to display.
     */
    private void displaySelectedItems(List<String> items){
        final String itemsAsString = Format.convertListToString(items, "\n");

        if(randItemKeepPrevious.isSelected()){
            randItemOutput.append(itemsAsString);
        }else{
            randItemOutput.setText(itemsAsString);
        }

        randItemOutput.append("\n\n");
    }

    /**
     * Clears the random item picker input box
     */
    private void clearRandomItemInput(){
        randItemInput.setText("");
    }

    /**
     * Clears the random item picker output box.
     */
    private void clearRandomItemOutput(){
        randItemOutput.setText("");
    }
}
