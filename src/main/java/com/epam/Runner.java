package com.epam;

import com.epam.commands.StartCommands;


public class Runner {
    private static final String PROPERTIES_FILE = "properties";
    private static final String PROPERTIES_KEY_PATH_READ = "read";
    private static final String PROPERTIES_KEY_PATH_WRITE = "write";


    private static StartCommands startCommands;
    private static PropertiesResourceManager props;
    private static String readFilePath;
    private static String writeFilePath;


    public static void main(String[] args) {
        //if there no parameters in command line, parameters will take from "properties" file
        if (args.length == 0) {
            initProperties();
            if (!readFilePath.equals(writeFilePath))
                readAndWriteResult();
            else
                System.out.println("File to read and to write is the same file.\nPlease enter paths to different files.");
        }
        //if only one parameter
        if (args.length == 1) {
            System.out.println("There is only one path to file.\nPlease enter two different paths to files.");
        }
        if (args.length == 2) {
            readFilePath = args[0];
            writeFilePath = args[1];
            readAndWriteResult();
        }
        //if more than two parameters
        if (args.length > 2) {
            System.out.println("There are more than two path to file.\nPlease enter two different paths to files.");
        }


    }

    //initialization properties and file path
    public static void initProperties() {
        props = new PropertiesResourceManager(PROPERTIES_FILE);

        readFilePath = System.getProperty(PROPERTIES_KEY_PATH_READ, props.getProperty(PROPERTIES_KEY_PATH_READ));
        writeFilePath = System.getProperty(PROPERTIES_KEY_PATH_WRITE, props.getProperty(PROPERTIES_KEY_PATH_WRITE));
    }

    public static void readAndWriteResult() {
        startCommands = new StartCommands(readFilePath);
        startCommands.startingCommandsFromFile();

        startCommands.writeResult(writeFilePath);
    }
}
