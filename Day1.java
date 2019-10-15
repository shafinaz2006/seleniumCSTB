package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day1 {

	public WebDriver driver;
	public WebDriverWait wait;
	//public Actions action = new Actions(driver);
	public String baseURL = "https://www.cstb.ca";	
	String driverPath = "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe";
	String expected;
	String actual;
	
	@DataProvider
	public String[][] getData(){
		
		String[][] data = new String[2][4];
		data[0][0] = "abc";
		data[0][1] = "abc@gmail.com";
		data[0][2] = "Hello";
		data[0][3] = "Message1";
		
		data[1][0] = "xyz";
		data[1][1] = "xyz@gmail.com";
		data[1][2] = "Hello2";
		data[1][3] = "Message2";
		
		return data;
		
	}
	
	@BeforeTest
	public void test1() {
		System.out.println("Hello from TestNG");
	}
	
	@BeforeTest
	public void setBaseURL() {
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	    driver.get(baseURL); 	    
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    
	}
	
	@Test
	public void verifyHomePageTitle() {
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get(baseURL); 
		
		expected = "CSTB";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void verifyExamsTab() {
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();
		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Exam Information')]")).click();
				
		expected = "https://cstb.ca/exams";
		actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void verifyPartnerProgramTab() {
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB® Partner Program')]"))).perform();
		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB® Partner Program Guidelines')]")).click();
			
		expected = "https://cstb.ca/istqb-partner-program-guidelines";
		actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void verifyTrainingProvidersTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(), 'ISTQB® Training Providers')]")).click();
			
		expected = "https://cstb.ca/accredited-training";
		actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void verifyCertifiedTestersTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(), 'Certified Testers')]")).click();
			
		expected = "https://cstb.ca/certified-testers";
		actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void verifyAboutUsTab() {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'About Us')]"))).perform();
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Contact Us')]")).click();
			
		expected = "https://cstb.ca/contact-us";
		actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test 
	public void screenExamsExamsInformation() {
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Exam Information')]")).click();

		String costInfo = driver.findElement(By.xpath("//h3[contains(text(), 'Cost')]/following-sibling::p[1]")).getAttribute("innerText");

		System.out.println(costInfo);
	}
	
	@Test
	public void screenExamsAdvancedLevelEntranceCriteria() {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Advanced Level Entrance Criteria')]")).click();

		List<WebElement> entranceCriteria = driver.findElements(By.xpath("//h2[contains(text(), 'Entrance Criteria')]//following-sibling::p//following-sibling::ul"));

		for(int i = 0; i < entranceCriteria.size(); i++) {

			System.out.println(entranceCriteria.get(i).getText());
		}
	}
	
	@Test
	public void screenExamsReferenceStudyMaterials(){
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Reference/Study Materials')]")).click();

		List<WebElement> bookInfoList = driver.findElements(By.xpath("//h3[contains(text(), 'Advanced Level Books')]//following-sibling::p"));

		for (int i = 0; i < bookInfoList.size(); i++) {

			String bookInfo = bookInfoList.get(i).getText();

			String bookName = bookInfo.substring(0, bookInfo.indexOf("by"));

			String price = bookInfo.substring(bookInfo.indexOf("List Price"), bookInfo.indexOf("Language"));

			String author = bookInfo.substring(bookInfo.indexOf("by"), bookInfo.indexOf("List Price"));

			System.out.println("Name of the book is: " + bookName + "\n" + price + "\nAuthor is: " + author);
	  
	   }
	 
	}
	
	@Test(dataProvider = "getData") 
	public void screenAboutUsContactUs(String name, String email, String subject, String message) {

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'About Us')]"))).perform();

		driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Contact Us')]")).click();

		String address = driver.findElement(By.xpath("//div[@class = 'row']//p[2]")).getText();
		String phoneInfo = driver.findElement(By.xpath("//div[@class = 'row']//p[5]")).getText();
		String personName = driver.findElement(By.xpath("//div[@class = 'row']//p[4]")).getText();

		System.out.println("Address: " + address + ", PhoneInfo: " + phoneInfo + ", Person Name: " + personName);

		driver.findElement(By.xpath("//div[@class = 'row']//p[4]//a[contains(text(), 'CSTB Manager')]")).click();
		driver.findElement(By.xpath("//input[@name = 'your-name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@name = 'your-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name = 'your-subject']")).sendKeys(subject);
		driver.findElement(By.xpath("//textarea[@name = 'your-message']")).sendKeys(message);
	  
	  }
	
	@Test
	public void screenAboutUsAskQuestion() {
		
		Actions action = new Actions(driver);
		
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'About Us')]"))).perform();
	    
	    //Sometimes drop down after hover over may cause exception - element not interactable. Few seconds wait works
	    
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Ask a Question')]")).click();
	    
	    String questionText = driver.findElement(By.xpath("//div[@class = 'row']//b")).getText();
	    
	    Assert.assertFalse(questionText.contains("error"));
	    
	}
	
	  @AfterTest 
	  public void closeWindow() {
	  
		  System.out.println("End of session"); 
		  driver.close();
	  
	  }
	 
	
	 
}
