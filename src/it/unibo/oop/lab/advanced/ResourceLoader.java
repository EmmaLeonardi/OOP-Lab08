package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader {
    /*
     * minimum, maximum, and number of allowed attempts are hard-coded Modify the
     * application in such a way that those settings are read from the supplied
     * configuration files (config.yml). An example of configuration file is
     * available in the res folder.
     * 
     * Do not make the View operate on the file system. More precisely, the
     * controller in its constructor must be able to read the provided configuration
     * file, and import the three constants. Even better, an external utility
     * (either a method or another class) could be responsible for that. The reason
     * is that the interaction with the file system is not part of the domain model,
     * nor, in this case, is part of the view.
     * 
     * You can use StringTokenizer to split a String in parts. Another option,
     * available for this very case, is String.split(), passing ":" as argument.
     * Beware of split(): the input String is actually a regular expression. Regular
     * expressions (regex) are a very powerful tool, but we can not cover them in
     * this course: StringTokenizer is recommended unless you already know regular
     * expressions and how to use them.
     */

    private int min;
    private int max = 100;
    private int att = 10;

    /**
     * @param f
     *              the file to read configurations on
     */
    public ResourceLoader(final File f) throws FileNotFoundException, IOException {
        String line;
        final int first = 0;
        final int second = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            line = br.readLine();
            final String[] parts = line.split(": ");
            if (parts.length == 2) {
                if (parts[first].equalsIgnoreCase("minimum")) {
                    min = Integer.parseInt(parts[second]);
                } else if (parts[first].equalsIgnoreCase("maximum")) {
                    max = Integer.parseInt(parts[second]);
                } else if (parts[first].equalsIgnoreCase("attempts")) {
                    att = Integer.parseInt(parts[second]);
                }
            } else {
                throw new IllegalArgumentException("Wrong paramenters in configuration file");
            }
        }
    }

    /**
     * @return the number of attempts read
     */
    public int getAttempts() {
        return att;
    }

    /**
     * @return the minimum number read
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the max number read
     */
    public int getMax() {
        return max;
    }

}
