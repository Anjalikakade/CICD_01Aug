package Resource;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendsReportClass 
{
	public static ExtentReports getReportObject()
	{
		File file = new File("C:\\\\Users\\\\softtech\\\\eclipse-workspace\\\\SeleniumFramework\\\\Reports\\index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(file);
		//set html page name and title
		reporter.config().setReportName("Automation");
		reporter.config().setDocumentTitle("Test Automation Title");
		ExtentReports extent = new ExtentReports();   // create extent class object
		extent.setSystemInfo("Tester", "Anjali Kakade");
		extent.attachReporter(reporter);
		return extent;
	}

}
