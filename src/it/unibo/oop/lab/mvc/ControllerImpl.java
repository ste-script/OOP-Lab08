package it.unibo.oop.lab.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> list = new ArrayList<>();
    private String string;

    /**
     * @throws ioexception */
    @Override
    public void setNextStringToPrint(final String s) throws IOException {
        if (s.isEmpty()) {
            throw new IOException();
        }
        this.string = s;
        this.list.add(this.string);

    }
    /**@return string */
    @Override
    public String getNextString() {
        return this.string;
    }

    /**@return list of strings */
    @Override
    public List<String> getStringHistory() {
        return List.copyOf(this.list);
    }

    /**@throws d */
    @Override
    public void printCurrentString() throws IllegalStateException {
        if (this.string == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.string);

    }

}
