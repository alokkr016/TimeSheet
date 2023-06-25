package functionalities;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class TimeSheet 
{
	
	List<String> dates=new ArrayList<String>();
	public WebDriver driver=null;
	Set<String> handles;
	ImpWait iw=new ImpWait();
	
	public TimeSheet(WebDriver d)
	{
		driver=d;
	}
	
	public void timeNav()
	{
		WebElement a=driver.findElement(By.xpath("//input[@aria-label='Search box. Suggestions appear as you type.']"));
		a.clear();
		a.sendKeys("TimeSheet");
		iw.wait(driver);
		
		//clicking the search button
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		iw.wait(driver);
		
		//clicking on the time sheet
		driver.findElement(By.xpath("//h2[contains(text(),'Timesheet Global')]")).click();
	}
	
	public List<String> date(String pw) throws IOException
	{
		handles=driver.getWindowHandles();
		for(String h:handles)
	     {
	    	 if(!h.equals(pw))
	    	 {
	    		 driver.switchTo().window(h);
	    		 
	    		 iw.wait(driver);
	    		 
	    		 try
	    		 {
	    			 driver.findElement(By.id("PTNUI_LAND_REC_GROUPLET_LBL$0")).click();
		    		 
		    		 iw.wait(driver);
		    		 
		    		 String d1=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$1")).getText();
		    		 String d2=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$2")).getText();
		    		 String d3=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$3")).getText();
		    		 
		    		 dates.add(d1);
		    		 dates.add(d2);
		    		 dates.add(d3);
		    		 
		    		 
		    		 Excel e=new Excel(driver);
		    		 e.putData("sheet1",dates );
		    		 
		    		 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    		 String path = System.getProperty("user.dir") + "\\screenshot\\TimeSheet.png";
		    		 FileUtils.copyFile(scrFile, new File(path));
	    		 }
	    		 catch(Exception e1)
	    		 {
	    			 //driver.findElement(By.id("PTNUI_LAND_REC_GROUPLET_LBL$0")).click();
		    		 
		    		 iw.wait(driver);
		    		 
		    		 String d1=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$1")).getText();
		    		 String d2=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$2")).getText();
		    		 String d3=driver.findElement(By.id("CTS_TS_LAND_PER_DESCR30$3")).getText();
		    		 
		    		 dates.add(d1);
		    		 dates.add(d2);
		    		 dates.add(d3);
		    		 
		    		 
		    		 Excel e=new Excel(driver);
		    		 e.putData("sheet1",dates );
		    		 
		    		 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    		 String path = System.getProperty("user.dir") + "\\screenshot\\TimeSheet.png";
		    		 FileUtils.copyFile(scrFile, new File(path));
	    		 }
	    	 }
	     }
		return dates;
	}
}
