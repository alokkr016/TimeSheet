package functionalities;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver 
{
	@Test
	public WebDriver getDriver(String name) throws IOException, InterruptedException 
	{
		Properties p = new Properties();
		FileReader reader = new FileReader("application.properties");        
	    p.load(reader);
	        
		WebDriver driver=null;
		
		if(name.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(op);
			driver.manage().window().maximize();
			driver.get(p.getProperty("baseUrl"));
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    String path = System.getProperty("user.dir") + "\\screenshot\\authentication.png";
		    FileUtils.copyFile(scrFile, new File(path));
			Thread.sleep(15000);
			return driver;
		}
		
		else
		{
			EdgeOptions op = new EdgeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(op);
			driver.manage().window().maximize();
			driver.get(p.getProperty("baseUrl"));
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    String path = System.getProperty("user.dir") + "\\screenshot\\authentication.png";
		    FileUtils.copyFile(scrFile, new File(path));
			Thread.sleep(15000);
			return driver;
		}
	}
}
