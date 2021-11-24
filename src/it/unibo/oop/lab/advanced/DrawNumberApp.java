package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> listView = new LinkedList<>();
    private final String s = System.getProperty("file.separator");
    private final File settings = new File("res" + s + "config.yml");

    /**
     * Imports configurations from res/config.yml.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public DrawNumberApp() throws FileNotFoundException, IOException {
        this.model = new DrawNumberImpl(settings);

        this.listView.add(new DrawNumberViewImpl());
        this.listView.add(new DrawNumberViewOnFile());
        this.listView.add(new DrawNumberViewOnStdout());
        for (final DrawNumberView view : listView) {
            view.setObserver(this);
            view.start();
        }

    }

    @Override
    public void newAttempt(final int n) {
        DrawResult result;
        try {
            result = model.attempt(n);
            for (final DrawNumberView view : listView) {
                try {
                    view.result(result);
                } catch (IllegalArgumentException e) {
                    view.numberIncorrect();
                }
            }
        } catch (AttemptsLimitReachedException e1) {
            for (final DrawNumberView view : listView) {
                view.limitsReached();
            }
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
            new DrawNumberApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
