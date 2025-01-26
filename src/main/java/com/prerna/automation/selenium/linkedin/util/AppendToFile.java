package com.prerna.automation.selenium.linkedin.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendToFile {
    public static void main(String[] args) {
        // Specify the file path
        String filePath = "path/to/your/file.txt";

        // Line to append
        String lineToAppend = "This is the line to append.";

        // Call the method to append the line to the file
        appendLineToFile(filePath, lineToAppend);
    }

    public static void appendLineToFile(String filePath, String line) {
        try {
            // Create a File object
            File file = new File(filePath);

            // Create a FileWriter with append mode set to true
            FileWriter fileWriter = new FileWriter(file, true);

            // Create a BufferedWriter to write the line
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the line to the file
            bufferedWriter.write(line);
            bufferedWriter.newLine();  // Move to a new line after appending

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("Line appended successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }
}
