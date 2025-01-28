package com.prerna.automation.selenium.linkedin.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.prerna.automation.selenium.linkedin.bean.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {

	private Workbook workbook;
	private Sheet currentSheet;

	public Excel() {
		workbook = new XSSFWorkbook();
	}

	public void createSheet(String sheetName) {
		currentSheet = workbook.createSheet(sheetName);
	}

	public void saveExcel(String filename) {
		// Write the data to an Excel file
		try (FileOutputStream fileOut = new FileOutputStream("D:\\Temp\\Files\\" + filename + ".xlsx")) {
			workbook.write(fileOut);
			System.out.println("Excel file created successfully with multiple sheets.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createSheetData(ArrayList<Person> personList) {
		Row headerRow = currentSheet.createRow(0);

		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Job Title");
		headerRow.createCell(2).setCellValue("Company Name");
		headerRow.createCell(3).setCellValue("Location");

		headerRow.createCell(4).setCellValue("Phone");
		headerRow.createCell(5).setCellValue("Hand Phones");
		headerRow.createCell(6).setCellValue("Website");
		headerRow.createCell(7).setCellValue("Social");
		headerRow.createCell(8).setCellValue("Emails");

		// Add some data
		for (int i = 1; i <= personList.size(); i++) {
			Person person = personList.get(i - 1);
			Row row = currentSheet.createRow(i);

			for (int j = 0; j < 8; j++) {
				row.createCell(j).setCellValue("" + person.getCellValue(j));
			}
		}
	}
}