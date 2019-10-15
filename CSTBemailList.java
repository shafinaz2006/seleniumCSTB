package selenium1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CSTBemailList {

	public static void main(String[] args) throws IOException {
		
		System.out.println("hello Selenium");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    
	    driver.get("https://www.cstb.ca");
	    
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    
	    //*****************************************************************************************************
	    //Testing email subscription box: 
	    
	    emailSubscriptionTesting(driver); 
	    
	    //*****************************************************************************************************

	    driver.findElement(By.xpath("//a[contains(text(), 'Certified Testers')]")).click();
	    
	    // Drop Down for all Certificate level: 
	    
	    Select certLevel = new Select(driver.findElement(By.xpath("//select[@name = 'certlevelid']")));
	    
	    List<WebElement> certLevelOptions = certLevel.getOptions();
	    
	    for (int i = 1; i < certLevelOptions.size(); i++) {
	    	
	    	System.out.println(certLevelOptions.get(i).getText());
	    }
	    
	    //certLevel.selectByIndex(1);
	    
	    //Initiating Excel: 
	    
	    FileInputStream fis = new FileInputStream("C:\\Selenium_jars\\ex1.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		String testerName = workbook.getSheetName(0);
			
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		FileOutputStream fos = new FileOutputStream("C:\\Selenium_jars\\ex1.xlsx");
		
	    	   
	    //Searching by testerName and creating the list:
		
	    driver.findElement(By.xpath("//input[@id = 'search']")).sendKeys(testerName);
	    
	    driver.findElement(By.xpath("//button[@value = 'Search']")).click();
	    
	    List<WebElement> testerNameList = driver.findElements(By.xpath("//div[@class = 'post-listings']//li[contains(text(), testerName)]"));
	    
	    System.out.println("Number of tester with name: " + testerName + " is: " +testerNameList.size());
	    
	    //Writing in Excel: 
	    
	    for (int i = 0; i < testerNameList.size(); i++) {
	    	
	    	String[] fullInfo = testerNameList.get(i).getText().split("[,:]");
	    	
	    	XSSFRow newRow = sheet.createRow(rowCount + 1 + i);
	    	
	    	for (int j = 0; j <fullInfo.length; j++) {
	    		
	    		newRow.createCell(j).setCellValue(fullInfo[j]);
	    		
	    	}
	    	
	    }
	    
	    fis.close();
		
		workbook.write(fos);
	    
		//to test is the tester is certified/not
		
		//String testerName1 = "Mark John";
		
	    //Boolean testerNameDisplay = driver.findElement(By.xpath("//li[contains(text(), testerName1)]")).isDisplayed();
	    
	    //System.out.println("Tester is certified: " + testerNameDisplay);
	   
	    System.out.println("End of session");
	    
	    workbook.close();
	    fos.close();
	    
	    driver.close();
	    
	}

	private static void emailSubscriptionTesting(WebDriver driver) {
		// TODO Auto-generated method stub
		
		driver.findElement(By.xpath("//input[@value = 'Subscribe']")).click();
	    
	    Boolean errMsg = driver.findElement(By.xpath("//div[@for = 'mce-EMAIL']")).isDisplayed();
	    
	    if (errMsg) {
	    	
	    	System.out.println("Error message is displayed when email address is not provided");
	    }    
	    
	    driver.findElement(By.xpath("//input[@name = 'EMAIL']")).sendKeys("xyz123@abc.com");
	    driver.findElement(By.xpath("//input[@name = 'FNAME']")).sendKeys("SSSS");
	    driver.findElement(By.xpath("//input[@name = 'LNAME']")).sendKeys("SSSS");
	    
	}

}
