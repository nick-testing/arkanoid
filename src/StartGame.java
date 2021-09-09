import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import game.animation.AnimationRunner;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;

import java.util.ArrayList;
import java.util.List;


/**
 * <h2>StartGame Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class StartGame {

    /**
     * returns true if the given string is an integer, false otherwise.
     * @param a String
     * @return true if int, false otherwise
     */
    public static boolean isInt(String a) {
        try {
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * returns the argument array represented as integers.
     * @param args String array to convert
     * @return a List of Integers
     */
    public static List<Integer> inputCorrection(String[] args) {
        List<Integer> intArgs = new ArrayList<Integer>();
        List<String> argsList = new ArrayList<String>();
        for (String s : args) {
            argsList.add(s);
            if (!isInt(s)) {
                argsList.remove(s);
                continue;
            }
            intArgs.add(Integer.parseInt(s));
        }
        return intArgs;
    }

    /**
     * Main method - creates a new Game and runs it.
     * @param args String[]
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", 800, 600);
        List<LevelInformation> levelList = new ArrayList<LevelInformation>();
        for (int i : inputCorrection(args)) {
            if (i == 1) {
                levelList.add(new Level1());
            } else if (i == 2) {
                levelList.add(new Level2());
            } else if (i == 3) {
                levelList.add(new Level3());
            } else if (i == 4) {
                levelList.add(new Level4());
            }
        }
        AnimationRunner ar = new AnimationRunner(gui, 60);
        GameFlow gf = new GameFlow(ar, gui.getKeyboardSensor());
        gf.runLevels(levelList);
        gui.close();
    }
}
