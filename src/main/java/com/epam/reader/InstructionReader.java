package com.epam.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class InstructionReader {

    private ArrayList<String> instructions = new ArrayList<>();
    private BufferedReader reader;

    public void initDriver(String filePath) {
        reader = Reader.getReader(filePath);
    }

    //take all instructions from file and write to ArrayList - instructions
    public void setInstructions() {

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0)
                    instructions.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Can not read from file.");
        }
        if (instructions.size() != 0) {
            {
                //delete firs symbol "-", from first line
                if (instructions.get(0).substring(0, 1).equals("\uFEFF"))
                    instructions.set(0, instructions.get(0).substring(1));
            }
        }
        Reader.closeReader();
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }
}
