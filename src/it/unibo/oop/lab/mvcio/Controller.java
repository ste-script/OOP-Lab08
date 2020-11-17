package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {
    /** Controller of the project. */
    private File file;

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     *
     * 1) A method for setting a File as current file
     */
    /**Setter of the file.
     * @param file file to set.*/
    public void setFile(final File file) {
        this.file = file;
    }
     /* 2) A method for getting the current File
     */
    /**Getter of the current file.
     * @return current file */
    public File getFile() {
        return this.file;
    }
     /* 3) A method for getting the path (in form of String) of the current File
     */

    /**Get the path of the file. 
     * @return the path*/
    public String getPath() {
        return this.file.getPath();
    }
     /* 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */
    /**Method to save a string into the file.
     * @throws IOException
     * @param s String in to write*/
    public void writeString(final String s) throws IOException {
        try (PrintStream ps = new PrintStream(this.file)) {
            ps.print(s);
        }
    }
     /* 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    Controller() {
        final String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";
        this.file = new File(filePath);
    }

}
