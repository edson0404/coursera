package mooc.vandy.java4android.diamonds.logic;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
        implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out) {
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        int counter = -(size + 1);
        int row = (size * 2) + 1;
        int column = (size * 2) + 2;
        for (int i = 1; i <= row; i++) {
            counter++;
            for (int j = 1; j <= column; j++) {
                printBorder(size, i, j, counter, row, column);
            }
            mOut.println("");
        }
    }

    private void printBorder(int size, int outerLoop, int innerLoop, int counter, int row, int column) {
        if ((outerLoop == 1 || outerLoop == row) && (innerLoop == 1 || innerLoop == column))
            mOut.print("+");
        else if ((outerLoop == 1 || outerLoop == row))
            mOut.print("-");
        else if ((innerLoop == 1 || innerLoop == column))
            mOut.print("|");
        else
            printDiamond(size, outerLoop, innerLoop, counter);
    }

    private void printDiamond(int size, int outerLoop, int innerLoop, int counter) {
        int diamondRow;

        if (counter <= 0)
            diamondRow = outerLoop * 2 - 2;
        else
            diamondRow = (outerLoop - counter * 2) * 2 - 2;

        int diamondCenter = size + 1;
        int diamondLeft = diamondCenter - (diamondRow / 2 - 1);
        int diamondRight = diamondCenter + (diamondRow / 2);
        int frameTop = 1;
        int frameBottom = size * 2 + 1;

        if (innerLoop >= diamondLeft && innerLoop <= diamondRight) {
            if (innerLoop == diamondLeft || innerLoop == diamondRight) {
                if (outerLoop < diamondCenter && outerLoop > frameTop)
                    printDiamondFrame(innerLoop, outerLoop, diamondLeft, diamondCenter, diamondRight);
                else if (outerLoop == diamondCenter)
                    printCenterDiamond(outerLoop, innerLoop, diamondRight, diamondLeft);
                else if (outerLoop > diamondCenter && outerLoop < frameBottom)
                    printDiamondFrame(innerLoop, outerLoop, diamondLeft, diamondCenter, diamondRight);
            } else
                printCenterDiamond(outerLoop, innerLoop, diamondRight, diamondLeft);
        } else
            mOut.print(" ");
    }

    private void printDiamondFrame(int innerLoop, int outerLoop, int diamondLeft, int diamondCenter, int diamondRight) {
        if (outerLoop < diamondCenter) {
            if (innerLoop == diamondLeft)
                mOut.print("/");
            else
                mOut.print("\\");
        } else {
            if (innerLoop == diamondRight)
                mOut.print("/");
            else
                mOut.print("\\");
        }
    }

    private void printCenterDiamond(int outerLoop, int innerLoop, int diamondRight, int diamondLeft) {
        if (innerLoop == diamondRight)
            mOut.print(">");
        else if (innerLoop == diamondLeft)
            mOut.print("<");
        else if (outerLoop % 2 == 0)
            mOut.print("=");
        else
            mOut.print("-");
    }

}
