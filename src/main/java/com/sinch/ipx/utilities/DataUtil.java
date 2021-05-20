package com.sinch.ipx.utilities;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.sinch.ipx.base.Testbase;

public class DataUtil extends Testbase {
	
	static Fillo fillo;
	static Connection connection;
	final static String excelFilePath = System.getProperty("user.dir") + prp.getProperty("filepath");
	static String connectionString;

	public static Object[][] getTestData(String excelWorksheetName, String testCaseName) throws FilloException {
		String rowNum = null;
		try {
			rowNum = getRowNumber(excelWorksheetName, testCaseName);
			System.out.println("rowNumber:"+rowNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("ROW", rowNum);// Table start row
		System.setProperty("COLUMN", "1");// Table start column

		connectionString = "select * from " + excelWorksheetName + " where testcase_name='" + testCaseName + "'";
System.out.println("path:"+excelFilePath);
		fillo = new Fillo();
		if (connection == null) {
			connection = fillo.getConnection(excelFilePath);
		}
		Recordset recordset = connection.executeQuery(connectionString);
		System.out.println("recordset count:"+recordset.getCount());

		Hashtable<String, String> table;
		Object[][] data = new Object[recordset.getCount()][1];
		int rowIndex = 0;
		while (recordset.next()) {
			table = new Hashtable<String, String>();
			for (String strColumn : recordset.getFieldNames()) {
				table.put(strColumn, recordset.getField(strColumn.toString()));
			}
			data[rowIndex][0] = table;
			rowIndex++;
		}
		recordset.close();
		return data;
	}

	private static String getRowNumber(String sheetName, String testcaseName) throws IOException {
		int rowNumber = 0;
		XSSFWorkbook workbook = new XSSFWorkbook(excelFilePath);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row r;
		Cell c;
		Iterator<Row> iterator = sheet.rowIterator(); // Returns an iterator of the physical rows
		while (iterator.hasNext()) {
			r = iterator.next();
			c = r.getCell(0);
			if (c != null) {
				if (c.getStringCellValue().equals(testcaseName)) {
					rowNumber = r.getRowNum(); //get row number this row present
					System.out.println("get row number this row present:"+rowNumber);
					break;
				}
			}
		}
		workbook.close();
		return String.valueOf(rowNumber);
	}
// use to get coloumn data
	public static String[] getSingleColumnData(String excelFilePath, String fileName, String excelWorksheetName,
			String columnName) throws FilloException {
		if (fileName.toLowerCase().contains(TestUtil.COUNTRY_TEXT)) {
			System.setProperty("ROW", "1");
		} else {
			System.setProperty("ROW", "2");// Table start row
		}
		System.setProperty("COLUMN", "1");// Table start column
		connectionString = "select * from \"" + excelWorksheetName + "\"";

		fillo = new Fillo();
		connection = fillo.getConnection(excelFilePath);
		Recordset recordset = connection.executeQuery(connectionString);

		int rowCount = recordset.getCount();
		System.out.println("Total records: " + rowCount);
		
		String[] data = new String[rowCount];
		int i = 0;
		while (recordset.next()) {
			data[i] = recordset.getField(columnName);
			i++;
		}
		recordset.close();
		return data;
	}

}
