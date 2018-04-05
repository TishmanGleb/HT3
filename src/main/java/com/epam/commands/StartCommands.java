package com.epam.commands;


import com.epam.reader.InstructionReader;
import com.epam.timer.Timer;
import com.epam.writer.WriterResult;


public class StartCommands {

    private final String OPEN = "open";
    private final String CHECK_LINK_PRESENT_BY_HREF = "checkLinkPresentByHref";
    private final String CHECK_LINK_PRESENT_BY_NAME = "checkLinkPresentByName";
    private final String CHECK_PAGE_TITLE = "checkPageTitle";
    private final String CHECK_PAGE_CONTAINS = "checkPageContains";

    private StringBuilder result = new StringBuilder();
    private String strSuccses = "+ "; //if the method passed
    private String strFail = "! ";   //if the method fails
    private String instruction = "";
    private String text = "";


    private int successTest = 0;
    private int failTest = 0;
    private int allTest = 0;
    private double allDuration;


    private InstructionReader instructionReader;
    private Command command;
    private Timer timer;
    private WriterResult writer;


    public StartCommands(String path) {

        instructionReader = new InstructionReader();
        instructionReader.initDriver(path);
        instructionReader.setInstructions();
    }


    public void startingCommandsFromFile() {
        command = new Command();
        for (String line : instructionReader.getInstructions()) {

            try {
                instruction = line.substring(0, line.indexOf(" ")); //take instruction
                text = line.substring(line.indexOf(" ")).trim().replaceAll("\"", "");
            } catch (Exception e) {
                System.out.println("Failed read instruction [" + line + "] from file.");
                continue;
            }

            timer = new Timer();

            switch (instruction) {
                case OPEN: {
                    String url = text.substring(0, text.indexOf(" ")); //take only page link
                    checkTest(command.open(url, getTimeout(text)));
                    break;
                }
                case CHECK_LINK_PRESENT_BY_HREF: {
                    checkTest(command.checkLinkPresentByHref(text));
                    break;
                }
                case CHECK_LINK_PRESENT_BY_NAME: {
                    checkTest(command.checkLinkPresentByName(text));
                    break;
                }
                case CHECK_PAGE_TITLE: {
                    checkTest(command.checkPageTitle(text));
                    break;
                }
                case CHECK_PAGE_CONTAINS: {
                    checkTest(command.checkPageContains(text));
                    break;
                }
            }
            double duration = timer.duration();
            allDuration += duration;
            result.append("[" + line + "] ").append(String.format("%.3f\r\n", duration));
        }
        allTest = successTest + failTest;
        result.append("Total tests: " + allTest)
                .append("\r\nPassed/Failed: " + successTest + "/" + failTest);
        if (allTest > 0)
            result.append("\r\nAverage time: " + String.format("%.3f", allDuration / allTest));
        else
            result.append("\r\nAverage time: 0,000");
    }

    //parse Sting from inputFile to Timeout
    private int getTimeout(String instruction) {
        try {
            return Integer.valueOf(instruction.trim().substring(instruction.lastIndexOf(" ") + 1));
        } catch (Exception e) {
            System.out.println("Can't parse [" + instruction + "] to time.");
            return 0;
        }
    }

    //write result to file(path)
    public void writeResult(String path) {
        writer = new WriterResult(path);
        writer.writeResultToFile(result);
    }

    //return true and inc success if the method passed
    //return false and inc failTest if the method fails
    public void checkTest(boolean testResult) {
        if (testResult) {
            successTest++;
            result.append(strSuccses);
        } else {
            failTest++;
            result.append(strFail);
        }
    }
}
