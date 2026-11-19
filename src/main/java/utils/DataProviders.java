package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider
	public Object[][] usersData() {
		try {
			FileInputStream fin = new FileInputStream("./src/test/resources/UsersData.xlsx");
			
			XSSFWorkbook workbook = new XSSFWorkbook(fin);
			XSSFSheet sheet = workbook.getSheet("Users");
			int rows = sheet.getPhysicalNumberOfRows();
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();
			
			String[][] data = new String[rows-1][cells];
			DataFormatter formatter = new DataFormatter();
			
			if(rows > 0 && cells > 0) {
				for(int i = 1; i < rows; i++) {
					XSSFRow row = sheet.getRow(i);
					for(int j = 0; j < cells; j++) {
						data[i-1][j] = formatter.formatCellValue(row.getCell(j));
					}
				}
			}
			
			workbook.close();
			fin.close();
			return data;
		}
		catch(Exception e) { 
			return null;
		}
	}
	
	
	
	@DataProvider
	public Object[][] singleUserData() {
		try {
			FileInputStream fin = new FileInputStream("./src/test/resources/UsersData.xlsx");
			
			XSSFWorkbook workbook = new XSSFWorkbook(fin);
			XSSFSheet sheet = workbook.getSheet("SingleUser");
			int rows = sheet.getPhysicalNumberOfRows();
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();
			
			String[][] data = new String[rows-1][cells];
			DataFormatter formatter = new DataFormatter();
			
			if(rows > 0 && cells > 0) {
				for(int i = 1; i < rows; i++) {
					XSSFRow row = sheet.getRow(i);
					for(int j = 0; j < cells; j++) {
						data[i-1][j] = formatter.formatCellValue(row.getCell(j));
					}
				}
			}
			
			workbook.close();
			fin.close();
			return data;
		}
		catch(Exception e) { 
			return null;
		}
	}

}
