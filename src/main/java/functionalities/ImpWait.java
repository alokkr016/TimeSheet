package functionalities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class ImpWait 
{
	WebDriver driver=null;
	public void wait(WebDriver driver)
	{
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}
}
