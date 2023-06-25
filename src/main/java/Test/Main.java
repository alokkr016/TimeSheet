package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functionalities.Driver;
import functionalities.GetDay;
import functionalities.HomePage;
import functionalities.ImpWait;
import functionalities.Report;
import functionalities.TimeSheet;
import functionalities.Valid;

public class Main {
	public WebDriver driver;

	Report report = new Report();

	GetDay a = new GetDay();

	ImpWait iw = new ImpWait();

	@BeforeClass
	public void beforeC() {
		System.out.println("Report will generate after all the test case executed");
	}

	@Parameters("browser")
	@BeforeMethod
	public void setDriver(@Optional("chrome") String browser) throws IOException, InterruptedException {
		Driver obj = new Driver();
		driver = obj.driver(browser);
	}

	@Test(enabled = true, priority = 1)
	public void test1() throws InterruptedException, IOException {
		// adding the test name in the extent report
		report.testName("Verifying the User Information");

		// adding the log in the report
		report.logsInfo("Opening the browser");

		// creating object of the HomePage class
		HomePage hp = new HomePage(driver);
		Thread.sleep(15000);
		iw.wait(driver);

		report.logsInfo("Going to the user details page");
		List<String> a = hp.userDetails();
		report.logsInfo("Taking the screenshot of user details page");

		// Verifying the name and the email using the hard assertions
		Assert.assertEquals(a.get(0), "Kumar, Alok (Contractor)");
		report.logsInfo("Verified the name");
		Assert.assertEquals(a.get(1), "Alok.Kumar106b23@cognizant.com");
		report.logsInfo("Verified the email");
	}

	@Test(enabled = true, priority = 2)
	public void test2() throws InterruptedException, IOException {
		report.testName("Time Sheets dates");

		// getting the parent window unique value
		String parentWindow = driver.getWindowHandle();
		report.logsInfo("Taking the parent window handle value");

		// creating object of the TimeSheet class
		TimeSheet ts = new TimeSheet(driver);
		report.logsInfo("creating the TimeSheet class object");
		report.logsInfo("Opening the browser");
		report.logsInfo("Waiting to get website load");
		iw.wait(driver);

		ts.timeNav();
		iw.wait(driver);
		List<String> d = ts.date(parentWindow);
		report.logsInfo("we get the time dates");

		// creating object of the Valid class
		Valid v = new Valid();

		Map<String, String> map1 = v.dict(d);
		report.logsInfo("From Valid class returning a MAP");

		Set<String> keys = map1.keySet();
		report.logsInfo("From map1 taking all the keys");

		List<String> arr = new ArrayList<String>(keys);
		report.logsInfo("converting the set into the list");

		System.out.println(map1);
		report.logsInfo("Printing the key values pairs");

		// Verifying the day hard assertions
		// a.day(arr.get(0));
		Assert.assertEquals(a.day(arr.get(0)), "SATURDAY");
		report.logsInfo("Verified the day");
	}

	@Test(enabled = true, priority = 3)
	public void verifyTimeSheetByStatus() throws InterruptedException {
		report.testName("Verifying the status by applying different filters");
		String parentWindow = driver.getWindowHandle();
		report.logsInfo("Taking the parent window handle value");
		
		TimeSheet ts = new TimeSheet(driver);
		report.logsInfo("creating the TimeSheet class object");
		report.logsInfo("Opening the browser");
		report.logsInfo("Waiting to get website load");
		iw.wait(driver);

		ts.timeNav();
		iw.wait(driver);
		Set<String> handles=driver.getWindowHandles();
		for(String h:handles)
	     {
	    	 if(!h.equals(parentWindow))
	    	 {
	    		 driver.switchTo().window(h);
	    		 
	    		 iw.wait(driver);
	    		 
	    		 try
	    		 {
	    			 driver.findElement(By.id("PTNUI_LAND_REC_GROUPLET_LBL$0")).click();
	    		 }catch (Exception e) {
					
				}
	    	 }
	     }
		
		
		Select searchBy = new Select(driver.findElement(By.className("ps-dropdown")));

		searchBy.selectByVisibleText("Status");
		report.logsInfo("Search by status");
		Thread.sleep(5000);

		
		clickDropDown("APR");
		report.logsInfo("for approved timesheets");
		
		clickDropDown("OVR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for overdue timesheets");

		clickDropDown("PAR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for partially approved timesheets");
		
		clickDropDown("PND");
//	    driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for pending timesheets");

		clickDropDown("SAV");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);

		clickDropDown("SBR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);

		clickDropDown("SUB");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
	}

	public void clickDropDown(String value) throws InterruptedException {
		Select filter = new Select(driver.findElement(By.xpath("(//select[@class='ps-dropdown'])[2]")));
		Thread.sleep(5000);
		filter.selectByValue(value);

		driver.findElement(By.id("CTS_TS_LAND_WRK_SEARCH$span")).click();
		Thread.sleep(5000);

		List<WebElement> list = driver
				.findElements(By.xpath("//div[@class='ps_box-group psc_layout card_group_box  green_color_badge']"));
		int size = list.size();

		for (int i = 0; i < size; i++) {
			String date = driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$" + i)).getText();
			String status = driver.findElement(By.id("CTS_TS_LAND_PER_CTS_TS_STATUS_LAND$" + i)).getText();
			Thread.sleep(500);
			System.out.println(date + "\n" + status);
		}
		Thread.sleep(5000);
	}

	@AfterMethod
	public void closeDriver() {
		// System.out.println("hi");
//		driver.quit();
	}

	@AfterClass
	public void afterC() throws IOException {
		report.flushR();
	}

}
