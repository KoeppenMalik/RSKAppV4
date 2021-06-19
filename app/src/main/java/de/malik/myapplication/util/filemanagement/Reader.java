// Created on 29.01.2021, 16:15

package de.malik.myapplication.util.filemanagement;

import java.io.*;
import java.util.ArrayList;

public class Reader {

    /**
     * reads all the lines of the argument file
     * @param file the file which will be read
     * @return a arraylist of string which contains all lines of the argument file
     */
    public static ArrayList<String> readLines(File file) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return lines;
    }
}
