package selenium1;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWriteOperation {

	public static void main(String[] args) throws IOException {
		
		//Input Stream:
		
		FileInputStream fis = new FileInputStream("C:\\Selenium_jars\\ex1.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		String sheetName = workbook.getSheetName(0);
		
		System.out.println("How many sheets is this excel? : " + workbook.getNumberOfSheets());
		System.out.println("Sheet name is: " + sheetName);
			
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		XSSFRow newRow = sheet.createRow(rowCount + 1);
		
		//Output Stream:
		
		FileOutputStream fos = new FileOutputStream("C:\\Selenium_jars\\ex1.xlsx");
		
		newRow.createCell(0).setCellValue("Shafinaz");
		
		newRow.createCell(1).setCellValue("Shafique");
		
		//Input Stream close:
		
		fis.close();
		
		//Writing in workbook:
		
		workbook.write(fos);
		
		//Output Stream and workbook close:
		
		fos.close();
		
		workbook.close();
		
		
	}

}
