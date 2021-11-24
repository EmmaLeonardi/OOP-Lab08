package it.unibo.oop.lab.advanced;

public class DrawNumberViewOnStdout implements DrawNumberView {

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
        System.out.println("Started game!");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        System.out.println("Incorrect number, try again");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            System.out.println(res.getDescription());
            return;
        case YOU_WON:
            System.out.println(res.getDescription());
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
        System.out.println("A new game starts!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        System.out.println("You lost");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        System.out.println("Error: " + message);

    }

}
