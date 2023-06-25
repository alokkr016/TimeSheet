package functionalities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class HomePage 
{
	public static WebDriver driver;
	
	public HomePage(WebDriver d)
	{
		driver=d;
	}
	
	public void login() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@placeholder='Email, phone, or Skype']")).sendKeys("username");
		driver.findElement(By.xpath("//*[@id='idSIButton9']")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		
		Thread.sleep(20000);
		
		driver.findElement(By.xpath("//input[@value='No']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}
	
	
	
	public List<String> userDetails() throws IOException
	{
		List<String> values=new ArrayList<String>();
		
		String parentWindow=driver.getWindowHandle();
		
		
		driver.findElement(By.xpath("//div[@id='O365_MainLink_MePhoto']")).click();
		
		//click on the view account button
		driver.findElement(By.xpath("//a[@id='mectrl_viewAccount']")).click();
		
		Set<String> handles=driver.getWindowHandles();
		for(String h:handles)
	     {
	    	 if(!h.equals(parentWindow))
	    	 {
	    		driver.switchTo().window(h);
	    		String a=driver.findElement(By.xpath("//div[@class='ms-tileTitle ms-pii XFgcGLmNY_BgTKmTe_jA css-211']")).getText();
	    		String b=driver.findElement(By.xpath("//span[@class='ms-pii'][1]")).getText();
	    		 
	    		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    		String path = System.getProperty("user.dir") + "\\screenshot\\UserDetails.png";
	    		FileUtils.copyFile(scrFile, new File(path));
	    		 
	    		values.add(a);
	    		values.add(b);
	    		driver.close();
	    	 }
	    	driver.switchTo().window(parentWindow); 
	     }
		
		System.out.println(values);
		return values;
		
	}
	
}
