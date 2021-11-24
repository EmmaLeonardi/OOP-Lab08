package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DrawNumberViewOnFile implements DrawNumberView {

    private final PrintWriter out;

    /**
     * @throws FileNotFoundException
     *                                   if cannot find res/log.txt
     */
    public DrawNumberViewOnFile() throws FileNotFoundException {
        this(new File("res" + System.getProperty("file.separator") + "log.txt"));
    }

    /**
     * 
     * @param f
     *              File to print on.
     * @throws FileNotFoundException
     *                                   if f doesn't exist.
     */
    public DrawNumberViewOnFile(final File f) throws FileNotFoundException {
        this.out = new PrintWriter(f);

    }

    @Override
    /**
     * {@inheritDoc}
     */
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        out.println("Started game!");
        out.flush();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        out.println("Incorrect number, try again");
        out.flush();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            out.println(res.getDescription());
            out.flush();
            return;
        case YOU_WON:
            out.println(res.getDescription());
            out.flush();
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
        out.println("A new game starts!");
        out.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        out.println("You lost");
        out.flush();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        out.println("Error: " + message);
        out.flush();

    }

}
