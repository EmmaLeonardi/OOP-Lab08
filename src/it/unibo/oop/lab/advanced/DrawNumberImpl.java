package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private final int min;
    private final int max;
    private final int attempts;
    private int remainingAttempts;
    private final Random random = new Random();

    /**
     * @param min
     *                     minimum number
     * @param max
     *                     maximum number
     * @param attempts
     *                     the maximum number of attempts
     */

    private DrawNumberImpl(final int min, final int max, final int attempts) {
        this.min = min;
        this.max = max;
        this.attempts = attempts;
        this.reset();
    }

    /**
     * @param f
     *              the file of configurations from which to read the configuration
     * 
     * @throws FileNotFoundException
     *                                   if f doesn't exist
     * @throws IOException
     *                                   if the parameters in f are wrong
     */
    public DrawNumberImpl(final File f) throws FileNotFoundException, IOException {
        this(new ResourceLoader(f).getMin(), new ResourceLoader(f).getMax(), new ResourceLoader(f).getAttempts());
    }

    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }

    @Override
    public DrawResult attempt(final int n) throws AttemptsLimitReachedException {
        if (this.remainingAttempts <= 0) {
            throw new AttemptsLimitReachedException();
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
