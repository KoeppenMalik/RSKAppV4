package de.malik.myapplication.util.filemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RSKReader {

    /**
     * reads all the lines of <code>file</code> and puts the data as Strings into an ArrayList
     * @param file the file which will be read
     * @return an ArrayList with all the data of the file
     * @throws IOException if the given file is a folder or if the folder can not be found
     */
    public static ArrayList<String> readLines(File file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = createReader(file);
        String line;
        while ((line = reader.readLine()) != null)
            lines.add(line);
        if (reader != null)
            reader.close();
        return lines;
    }

    /**
     * creates a new instance of the <code>BufferedReader</code> class
     * @param file the file in which the reader will read
     * @return a new instance of <code>BufferedReader</code>
     * @throws IOException if the given file is a folder or if the folder can not be found
     */
    private static BufferedReader createReader(File file) throws IOException {
        FileReader fReader = new FileReader(file);
        return new BufferedReader(fReader);
    }
}
