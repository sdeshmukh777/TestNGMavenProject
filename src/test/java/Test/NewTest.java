
package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.ExcelApiTest;

public class NewTest {
	// Declared all Variables
	public WebDriver driver;

	@BeforeClass
	public void LaunchBrowser() throws Exception {
		// It create firefox profile
		FirefoxProfile profile = new FirefoxProfile();

		// This will set the true value
		profile.setAcceptUntrustedCertificates(true);
		// Launch Browser

		System.setProperty("webdriver.gecko.driver", "/root/swapnil/Selenium/geckodriver");
		driver = new FirefoxDriver();

		driver.get("https://10.10.61.140");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	@Parameters({ "sUname", "sPassword" })
	public void vwLogin(String sUname, String sPassword) throws InterruptedException {

		WebElement uname = driver.findElement(By.cssSelector("input[name=username]"));
		SendKeys(driver, uname, 10, sUname);

		WebElement pass = driver.findElement(By.cssSelector("input[name=password]"));
		SendKeys(driver, pass, 15, sPassword);

		WebElement lgclick = driver.findElement(By.cssSelector("button#login-btn"));
		ClickOn(driver, lgclick, 30);

		driver.findElement(By.xpath("//div[contains(@class,'x-box-layout-c')]//span[text()='OK']")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 1)
	public void AddVC() throws Exception {

		Thread.sleep(10000);
		WebElement s = driver.findElement(By.xpath("//div[contains(@id,'body')]//a[starts-with(@id,'tab')][7]"));
		ClickOn(driver, s, 120);
		Thread.sleep(10000);
		WebElement integ = driver.findElement(By.xpath("//div[contains(@id,'container')]/div[text()='Integrations']"));
		ClickOn(driver, integ, 120);
		Thread.sleep(5000);
		driver.findElement(
				By.xpath("(//div[contains(@class,'x-box-inner')]//a[contains(@id,'button')]//span[text()='View'])[7]"))
				.click();
		Thread.sleep(5000);
		boolean butnew = driver.findElement(By.xpath(
				"//div[contains(@id,'probe-management-sub-toolbar-')]/a[contains(@class,'x-btn-vw-sub-nav-toolbar-small')]//span[text()='New']"))
				.isDisplayed();

		if (butnew == true) {
			Thread.sleep(5000);
			driver.findElement(By.xpath(
					"//div[contains(@id,'probe-management-sub-toolbar-')]/a[contains(@class,'x-btn-vw-sub-nav-toolbar-small')]//span[text()='New']"))
					.click();
			FillVCDetails();
		}

		else {
			Thread.sleep(5000);
			FillVCDetails();
		}

	}

	@Test(priority = 2)
	public void addHyperV() throws InterruptedException {
		Thread.sleep(10000);
		WebElement s = driver.findElement(By.xpath("//div[contains(@id,'body')]//a[starts-with(@id,'tab')][7]"));
		ClickOn(driver, s, 120);
		Thread.sleep(10000);
		WebElement integ = driver.findElement(By.xpath("//div[contains(@id,'container')]/div[text()='Integrations']"));
		ClickOn(driver, integ, 120);
		Thread.sleep(5000);
		driver.findElement(
				By.xpath("(//div[contains(@class,'x-box-inner')]//a[contains(@id,'button')]//span[text()='View'])[6]"))
				.click();
		Thread.sleep(5000);
	}

	@AfterClass
	public void CloseBrowser() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.quit();
	}

	public void FillVCDetails() throws Exception {
		ExcelApiTest getdata = new ExcelApiTest("/root/swapnilVC.xls", "Sheet1");

		String hostname = getdata.getData(1, 0);
		String username = getdata.getData(1, 1);
		String password = getdata.getData(1, 2);
		// String certpath = getdata.getData(1, 3);
		// System.out.println(hostname + " " + username + " " + password + " " +
		// certpath);
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//div[contains(@id,'probe-vm-create-form-')]//input[starts-with(@id,'textfield') and @name='ipAddress']"))
				.sendKeys(hostname);
		Thread.sleep(1000);

		driver.findElement(By.xpath(
				"//div[contains(@id,'probe-vm-create-form-')]//input[starts-with(@id,'textfield') and @name='username']"))
				.sendKeys(username);
		Thread.sleep(1000);

		driver.findElement(By.xpath(
				"//div[contains(@id,'probe-vm-create-form-')]//input[starts-with(@id,'password') and @name='password']"))
				.sendKeys(password);
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("[id^=probe-vm-create-form] [name=certFile]"))
				.sendKeys("C:\\qe-vsan.lab.vi.local.crt");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[contains(@id,'vwformbottomtoolbar')]/a//span[text()='Next']")).click();
		Thread.sleep(15000);

		driver.findElement(By.xpath("(//div[starts-with(@id,'tabpanel')]//input[@name='name'])[1]"))
				.sendKeys("AutomatedVSAN");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='x-autocontainer-innerCt']//span[text()='Test Connection']")).click();
		Thread.sleep(10000);

		WebElement testconnText = driver
				.findElement(By.xpath("//div[@class='x-selectable short-message-view' and text()]"));
		System.out.println("Test Connection : " + testconnText.getText());
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[contains(@class,'x-box-layout-c')]//span[text()='OK']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(
				"//div[contains(@class,' x-tab-bar-vw-page-tabs-docked-top')]//a//span[text()='Discovered ESX Hosts']"))
				.click();
		Thread.sleep(5000);

		driver.findElement(By.cssSelector("[id*=checkcolumn] [class=x-column-header-checkbox]")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(
				"//div[contains(@class,'x-panel x-box-item x-panel-default')]//a[contains(@class,'x-btn')]//span[text()='Subscribe']"))
				.click();
		Thread.sleep(10000);

		driver.findElement(By
				.xpath("//div[contains(@class,' x-tab-bar-vw-page-tabs-docked-top')]//a//span[text()='vSAN Clusters']"))
				.click();
		Thread.sleep(7000);

		driver.findElement(By.xpath(
				"(//div[contains(@class,'x-column-header-text-wrapper')]//span[contains(@class,'x-column-header-checkbox')])[2]"))
				.click();
		Thread.sleep(5000);

		driver.findElement(By.xpath(
				"(//div[contains(@class,'x-panel x-box-item x-panel-default')]//a[contains(@class,'x-btn')]//span[text()='Subscribe'])[2]"))
				.click();
		Thread.sleep(10000);

		driver.findElement(
				By.xpath("(//div[contains(@class,'innerCt')]//span[contains(@id,'btnInnerEl') and text()='Save'])[3]"))
				.click();
		Thread.sleep(10000);

		driver.findElement(By.xpath(
				"//div[starts-with(@class,'x-window')]//a[contains(@class,'x-btn-confirm-small')]//span[text()='Yes']"))
				.click();
		Thread.sleep(15000);

		while (driver
				.findElement(By.xpath(
						"(//div[starts-with(@id,'sw-probes-grid')]//div[contains(@id,'gridview')]//div/table//td)[4]"))
				.getText().contains("Discovering...")) {

			driver.findElement(By.xpath(
					"(//div[starts-with(@id,'probe-management-layout')]//span[contains(@id,'vw-refresh-button')]/span/span)[1]"))
					.click();
			Thread.sleep(5000);
		}

		System.out.println((hostname.toUpperCase()) + " Probe Added Successfully in VW appliance");

		// Total VMware vCenter probe

		driver.findElement(By.xpath("//div[starts-with(@id,'breadcrumbs')]//span[text()='Integrations']")).click();
		Thread.sleep(2000);

		WebElement vcCount = driver
				.findElement(By.xpath("//div[starts-with(@id,'integration')]//div[contains(text(),'VMware vCenter')]"));
		System.out.println("Total VCenter Probe added : " + vcCount.getText());
		Thread.sleep(3000);

		vcEntityCount();
	}

	// Get Total VC Entity Details
	public void vcEntityCount() throws Exception {
		driver.findElement(By.xpath("//div[contains(@class,'x-tab-bar-vertical' )]//a[starts-with(@id,'tab')][3]"))
				.click();
		Thread.sleep(10000);

		WebElement vcCount = driver.findElement(By.xpath("(//div[contains(text(),'VMware vCenter')])[2]"));
		System.out.println("Total Discovred VMware vCenter Entity Count: " + vcCount.getText());
		Thread.sleep(3000);

		WebElement ecluster = driver.findElement(By.xpath("(//div[contains(text(),'ESX Cluster')]//span)"));
		System.out.println("Total Discovred ESX Cluster : " + ecluster.getText());
		Thread.sleep(3000);

		WebElement esxDatacenter = driver.findElement(By.xpath("(//div[contains(text(),'ESX Datastore')]//span)"));
		System.out.println("Total Discovred ESX Datastore : " + esxDatacenter.getText());
		Thread.sleep(3000);

		WebElement esxVm = driver.findElement(By.xpath("(//div[contains(text(),'ESX VM')]//span)"));
		System.out.println("Total Discovred ESX VM'S : " + esxVm.getText());
		Thread.sleep(3000);

		WebElement esxHost = driver.findElement(By.xpath("(//div[contains(text(),'ESX Host')]//span)"));
		System.out.println("Total Discovred ESX Host : " + esxHost.getText());
		Thread.sleep(3000);

	}

	// explicit wait
	public static void SendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);

	}

	public static void ClickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}

}
