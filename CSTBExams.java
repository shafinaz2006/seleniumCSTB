package selenium1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CSTBExams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    System.out.println("hello Selenium");
		
	    System.setProperty("webdriver.chrome.driver", "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    
	    driver.get("https://www.cstb.ca");
	    
	    driver.manage().window().maximize();

	    driver.manage().deleteAllCookies();
	    
	    Actions action = new Actions(driver);
	    
		
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Exam Information')]")).click();
	    
	    String costInfo = driver.findElement(By.xpath("//h3[contains(text(), 'Cost')]/following-sibling::p[1]")).getAttribute("innerText");
	    
	    System.out.println(costInfo);
	    
	    
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();
	    
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Advanced Level Entrance Criteria')]")).click();
	    
	    List<WebElement> entranceCriteria = driver.findElements(By.xpath("//h2[contains(text(), 'Entrance Criteria')]//following-sibling::p//following-sibling::ul"));
	    
	    for(int i = 0; i < entranceCriteria.size(); i++) {
	    		    	
	    	System.out.println(entranceCriteria.get(i).getText());
	    }
	    
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'Exams')]"))).perform();
	    
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Reference/Study Materials')]")).click();
	    
	    List<WebElement> bookInfoList = driver.findElements(By.xpath("//h3[contains(text(), 'Advanced Level Books')]//following-sibling::p"));
	    
	    String bookInfo;
	    String bookName;
            String price;
	    String author;
		
	    for (int i = 0; i < bookInfoList.size(); i++) {
	    	
	    	bookInfo = bookInfoList.get(i).getText();
	    
	    	bookName = bookInfo.substring(0, bookInfo.indexOf("by"));
		
	    	price = bookInfo.substring(bookInfo.indexOf("List Price"), bookInfo.indexOf("Language"));
		
	    	author = bookInfo.substring(bookInfo.indexOf("by"), bookInfo.indexOf("List Price"));
		
	    	System.out.println("Name of the book is: " + bookName + "\n" + price + "\nAuthor is: " + author);
	    	
	    }
	    
	    System.out.println("End of session");
	    
	    driver.close();
	    
	    
	}

}
