package main.java;

import main.java.app.Randomiser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Creates and launches the app.
 */
public class Launcher {
    /**
     * Provides an error message for when a fatal error has occurred
     * and the app cannot open.
     *
     * <p>This may be used when setting the look and feel, if no valid
     * LAF can be set.
     */
    private static final String FATAL_ERROR = "A fatal error occurred and the program could not be loaded.\n";

    /**
     * Provides an error message for when the app icon cannot be loaded.
     */
    private static final String ICON_CANNOT_BE_LOADED_ERROR = "Error: \"java/main/resources/icon.png\" could not be loaded.\n";

    public static void main(String[] args){
        setLookAndFeel();
        showApp();
    }

    /**
     * Sets the look and feel of the app to the system default.
     *
     * <p>If there is no system LAF, default to the cross-platform LAF.
     *
     * <p>Failing this, an error message is displayed and the program will exit.
     */
    private static void setLookAndFeel(){
        try{
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

            if(UIManager.getLookAndFeel().getName().equals("Windows")){
                setWindowsUIFont();
            }
        }catch(UnsupportedLookAndFeelException
                | ClassNotFoundException
                | InstantiationException
                | IllegalAccessException e){
            JOptionPane.showMessageDialog(null,
                    FATAL_ERROR + e.getMessage(),
                    Randomiser.TITLE, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Sets the default font of the app to the default Windows font.
     */
    private static void setWindowsUIFont(){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key,
                        new FontUIResource("Segoe UI", Font.PLAIN,12));
        }
    }

    /**
     * Creates and displays the main JFrame of the app.
     */
    private static void showApp(){
        JFrame frame = new JFrame(Randomiser.TITLE);
        frame.setContentPane(new Randomiser().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        setIcon(frame);
        frame.setVisible(true);
    }

    /**
     * Sets the icon of the JFrame.
     *
     * <p>If the icon cannot be loaded for any reason, an error message is
     * displayed to the user, however this will not stop the app from running.
     *
     * @param frame The main {@code JFrame} of the app.
     */
    private static void setIcon(JFrame frame){
        try{
            BufferedImage icon = ImageIO.read(ClassLoader.getSystemResource("icon.png"));
            frame.setIconImage(icon);
        }catch(IOException | IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,
                    ICON_CANNOT_BE_LOADED_ERROR, Randomiser.TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
