package selenium1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSTBTrainingProviders {
  public static void main(String[] args) {
    System.out.println("hello Selenium");
    System.setProperty("webdriver.chrome.driver", "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.cstb.ca");
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.findElement(By.xpath("//a[contains(text(), 'ISTQB� Training Providers')]")).click();
    List<WebElement> trainingProviderList = driver
        .findElements(By.xpath("//p[contains(text(), 'training provider')]//following-sibling::ul//li//a"));
    for (int i = 0; i < trainingProviderList.size(); i++) {
      System.out.println(trainingProviderList.get(i).getText());
      trainingProviderList.get(i).click();
      driver.navigate().back();
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      trainingProviderList = driver
          .findElements(By.xpath("//p[contains(text(), 'training provider')]//following-sibling::ul//li//a"));
    }
    driver.close();
    System.out.println("End of session");
  }
}
