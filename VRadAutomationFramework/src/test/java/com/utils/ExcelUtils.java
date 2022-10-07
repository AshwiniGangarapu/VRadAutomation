package com.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {

	public XSSFWorkbook getWorkbook(String filePath) throws IOException {
		CommonUtils cu = new CommonUtils(null);
		FileInputStream fis = cu.readFile(filePath);
		return new XSSFWorkbook(fis);
	}

	public XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) throws IOException {
		return workbook.getSheet(sheetName);
	}
    //method overriding
	public XSSFSheet getSheet(String filePath, int sheetNumber) throws IOException {
		return getWorkbook(filePath).getSheetAt(sheetNumber);
	}

	public XSSFCell getCellValue(XSSFSheet sheet, int rowNo, int CellNo) {
		return sheet.getRow(rowNo).getCell(CellNo);
	}

	public int getRowByName(XSSFSheet sheet, String cellName) {
		int cellCount = sheet.getRow(0).getLastCellNum();
		int cellNumber = 0;
		for (int i = 0; i < cellCount; i++) {
			String name = sheet.getRow(0).getCell(i).getStringCellValue();
			if (name.equalsIgnoreCase(cellName)) {
				cellNumber = i;
				break;
			}
		}
		return cellNumber;
	}

	XSSFCell getDataByColumnName(XSSFSheet sheet, String cellName, int rowNumber) {
		int cellno = getRowByName(sheet, cellName);
		return sheet.getRow(rowNumber).getCell(cellno);
	}

	public String getDataAsString(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getStringCellValue();
	}

	public double getDataAsNumeric(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getNumericCellValue();
	}

	XSSFCell getDataByColumnIndex(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return sheet.getRow(rowNumber).getCell(cellNumber);
	}

	public String getDataAsString(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getStringCellValue();
	}

	public double getDataNumeric(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getNumericCellValue();
	}
	

	public void writeToExcel(String fPath, String fSheet, String xData) throws IOException { 
		
		File file = new File(fPath);
		FileInputStream fis = new FileInputStream(file);		
		XSSFWorkbook wb = new  XSSFWorkbook(fis);
		 XSSFSheet sheet = wb.getSheet(fSheet);
		 sheet.getRow(1).createCell(3).setCellValue(xData);
		 File outFile = new File(fPath);
		 FileOutputStream fOut = new FileOutputStream(outFile); 
		 wb.write(fOut);
	}
		
    @DataProvider(name = "firstDP")
	public String[][] readExcelDataToArray(XSSFSheet sheet) {
		
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(colCount);
		
		String[][] data = new String[rowCount][colCount];
		
		for(int i=0;i<=rowCount-1;i++) {
			System.out.println(i);
			for(int j=0;j<=colCount-1;j++) {
				System.out.println(j);
				DataFormatter df = new DataFormatter();
				System.out.println(sheet.getRow(i+1).getCell(j));
				data[i][j]= df.formatCellValue(sheet.getRow(i+1).getCell(j));										
			}
			System.out.println();
			}
		for(String[] dataArr : data) {
			
			System.out.println(Arrays.toString(dataArr));
			
		}
		
		return data;
			
		}
	}
	
	

