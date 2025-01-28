package com.prerna.automation.selenium.linkedin.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWithMultipleSheets {
    public static void main(String[] args) {
        // Create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        
        // Create first sheet and add data
        Sheet sheet1 = workbook.createSheet("Sheet1");
        createSheetData(sheet1);

        // Create second sheet and add data
        Sheet sheet2 = workbook.createSheet("Sheet2");
        createSheetData(sheet2);

        // Create third sheet and add data
        Sheet sheet3 = workbook.createSheet("Sheet3");
        createSheetData(sheet3);

        // Write the data to an Excel file
        try (FileOutputStream fileOut = new FileOutputStream("multiple_sheets_example.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully with multiple sheets.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add data to a given sheet
    private static void createSheetData(Sheet sheet) {
        // Create the header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Age");

        // Add some data
        for (int i = 1; i <= 10; i++) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue("Name " + i);
            row.createCell(2).setCellValue(20 + i);
        }
    }
}