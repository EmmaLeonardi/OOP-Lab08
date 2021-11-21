package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final DrawNumberView view;
    private final String s = System.getProperty("file.separator");
    private final File settings = new File(new File("").getAbsolutePath() + s + "res" + s + "config.yml");

    /**
     * Imports configurations from res/config.yml.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public DrawNumberApp() throws FileNotFoundException, IOException {
        this.model = new DrawNumberImpl(settings);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *                 ignored
     */
    public static void main(final String... args) {
        try {
            // System.out.println(new File("").getAbsolutePath());
            // System.out.println(new File(new File("").getAbsolutePath() +
            // System.getProperty("file.separator") + "res"
            // + System.getProperty("file.separator") + "config.yml").getAbsolutePath());
            // System.out.println(prova2.getAbsolutePath());
            new DrawNumberApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
