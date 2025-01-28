package com.prerna.automation.selenium.linkedin.util;

import java.util.Random;

public class GeneralUtil {
	public static int generateRandom(int start, int end) {
		Random random = new Random();
		return start + random.nextInt(end);
	}
}
