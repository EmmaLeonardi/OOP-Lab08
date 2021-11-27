package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader {

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
