package Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class VC {
	public WebDriver driver;
	  @Test
	  public void Test123() {
		  System.out.println("Test Pass");
	  }
	  
	  public void Test1234() {
		  System.out.println("Test Pass");
	  }
	  @BeforeClass
	  public void beforeClass() {
		  
		  System.setProperty("webdriver.gecko.driver", "/root/swapnil/Selenium/geckodriver");
		  driver=new FirefoxDriver();
		  driver.get("http://10.10.60.237:8080/");
	  }

	  @AfterClass
	  public void afterClass() {
	  }

}
