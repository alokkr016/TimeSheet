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

	GetDay getDay = new GetDay();

	ImpWait implicitWait = new ImpWait();

	@BeforeClass
	public void beforeC() {
		System.out.println("Report will generate after all the test case executed");
	}

	@Parameters("browser")
	@BeforeMethod
	public void setDriver(@Optional("chrome") String browser) throws IOException, InterruptedException {
		Driver obj = new Driver();
		driver = obj.getDriver(browser);
	}

	@Test(enabled = true, priority = 1)
	public void test1() throws InterruptedException, IOException {
		// adding the test name in the extent report
		report.testName("Verifying the User Information");

		// adding the log in the report
		report.logsInfo("Opening the browser");

		// creating object of the HomePage class
		HomePage homePage = new HomePage(driver);
		Thread.sleep(15000);
		implicitWait.wait(driver);

		report.logsInfo("Going to the user details page");
		List<String> a = homePage.userDetails();
		report.logsInfo("Taking the screenshot of user details page");

		// Verifying the name and the email using the hard assertions
		Assert.assertEquals(a.get(0), "Kumar, Alok (Contractor)");
		report.logsInfo("Verified the name");
		Assert.assertEquals(getDay.get(1), "Alok.Kumar106b23@cognizant.com");
		report.logsInfo("Verified the email");
	}

	@Test(enabled = true, priority = 2)
	public void test2() throws InterruptedException, IOException {
		report.testName("Time Sheets dates");

		// getting the parent window unique value
		String parentWindow = driver.getWindowHandle();
		report.logsInfo("Taking the parent window handle value");

		// creating object of the TimeSheet class
		TimeSheet timesheet = new TimeSheet(driver);
		report.logsInfo("creating the TimeSheet class object");
		report.logsInfo("Opening the browser");
		report.logsInfo("Waiting to get website load");
		implicitWait.wait(driver);

		timesheet.timeNav();
		implicitWait.wait(driver);
		List<String> d = timesheet.date(parentWindow);
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
		
		TimeSheet timesheet = new TimeSheet(driver);
		report.logsInfo("creating the TimeSheet class object");
		report.logsInfo("Opening the browser");
		report.logsInfo("Waiting to get website load");
		implicitWait.wait(driver);

		timesheet.timeNav();
		implicitWait.wait(driver);
		Set<String> handles=driver.getWindowHandles();
		for(String h:handles)
	     {
	    	 if(!h.equals(parentWindow))
	    	 {
	    		 driver.switchTo().window(h);
	    		 
	    		 implicitWait.wait(driver);
	    		 
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

		HandleDropDown dropdown = new HandleDropDown();
		dropdown.clickDropDown("APR");
		report.logsInfo("for approved timesheets");
		
		dropdown.clickDropDown("OVR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for overdue timesheets");

		dropdown.clickDropDown("PAR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for partially approved timesheets");
		
		dropdown.clickDropDown("PND");
//	    driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
		report.logsInfo("for pending timesheets");

		dropdown.clickDropDown("SAV");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);

		dropdown.clickDropDown("SBR");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);

		dropdown.clickDropDown("SUB");
		driver.findElement(By.xpath("//*[@id=\"#ICOK\"]")).click();
		Thread.sleep(2000);
	}

	

	@AfterMethod
	public void closeDriver() {
		// System.out.println("hi");
		driver.quit();
	}

	@AfterClass
	public void afterC() throws IOException {
		report.flushR();
	}

}
