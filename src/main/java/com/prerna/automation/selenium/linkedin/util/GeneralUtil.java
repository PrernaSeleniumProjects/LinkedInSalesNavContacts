package com.prerna.automation.selenium.linkedin.util;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralUtil {
	public static int generateRandom(int start, int end) {
		Random random = new Random();
		return start + random.nextInt(end);
	}
	
	public static String generateFileName() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the desired format: YYYYMMDDHHMMSS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        
        // Format the current date and time
        return now.format(formatter) + ".xlsx"; // Adding the file extension (can be changed)
    }
}
