/**
 * 
 */
package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> list = new LinkedList<>();
    private String nextString;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String s) {
        if (s != null) {
            this.nextString = s;
        } else {
            throw new IllegalArgumentException("The argument cannot be null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return this.nextString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> historyOfStringsPrinted() {
        return new LinkedList<String>(this.list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printNextString() {
        if (nextString != null) {
            this.list.add(nextString);
            System.out.println(nextString);
            this.nextString = null;
        } else {
            throw new IllegalStateException("Cannot print an empty string");
        }
    }

}
