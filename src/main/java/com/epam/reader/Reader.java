package com.epam.reader;

import java.io.*;

public class Reader {

    private static BufferedReader reader;

    public static BufferedReader getReader(String path) {
        if (path.endsWith(".txt")) {
            if (new File(path).exists()) {
                if (reader == null) {
                    try {
                        reader = new BufferedReader(new FileReader(path));
                    } catch (FileNotFoundException e) {
                        System.out.println("Can't found file by path [" + path + "].\nPlease enter correct path.");
                    }
                }
            } else {
                System.out.println("File[" + path + "] to read instructions doesn't exist!");
                System.exit(0);
            }
        } else {
            System.out.println("File to read instructions must be [txt] format!");
            System.exit(0);
        }
        return reader;
    }

    public static void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Can't close reader");
        }
        reader = null;
    }
}
