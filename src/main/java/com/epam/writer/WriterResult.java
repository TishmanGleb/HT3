package com.epam.writer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterResult {

    BufferedWriter writer;

    public WriterResult(String path) {
        if (path.endsWith(".txt")) {
            if (new File(path).exists()) {
                try {
                    writer = new BufferedWriter(new FileWriter(path));
                } catch (IOException e) {
                    System.out.println("Can't not open file by path [" + path + "].");
                }
            } else {
                System.out.println("File [" + path + "] to write result, doesn't exist!");
                System.exit(0);
            }
        } else {
            System.out.println("File to write result must be [txt] format!");
            System.exit(0);
        }

    }

    public void writeResultToFile(StringBuilder result) {
        try {
            writer.write(result.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't write result to file.");
        }

    }
}
