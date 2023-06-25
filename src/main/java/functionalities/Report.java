package functionalities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report 
{
	Object a;
	ExtentTest test;
	
	public Report()
	{
		ExtentReports extent = new ExtentReports();
		a=extent;
		ExtentSparkReporter sp=new ExtentSparkReporter("Report.html");
		((ExtentReports) a).attachReporter(sp);
	}
	
	public void testName(String b)
	{
		test = ((ExtentReports) a).createTest(b);
	}
	
	public void logsInfo(String b)
	{
		test.log(Status.INFO,b);
	}
	
	public void flushR() throws IOException
	{
		((ExtentReports) a).flush();
		 Desktop.getDesktop().browse(new File("Report.html").toURI());
	}
	
}
