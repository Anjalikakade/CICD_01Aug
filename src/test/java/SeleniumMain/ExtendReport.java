package SeleniumMain;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReport 
{
	ExtentReports extent;
	
	//extend report add depenadcy and create object of Extendrepots and extendsparkrepors class
	@BeforeMethod
	public void config()
	{
		File file = new File("C:\\\\Users\\\\softtech\\\\eclipse-workspace\\\\SeleniumFramework\\\\Reports\\index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(file);
		//set html page name and title
		reporter.config().setReportName("Automation");
		reporter.config().setDocumentTitle("Test Automation Title");
		extent = new ExtentReports();   // create extent class object
		extent.setSystemInfo("Tester", "Anjali Kakade");
		extent.attachReporter(reporter);
		
		 
		 
		
	}
	@Test
	public void invoke()
	{
		ExtentTest test =extent.createTest("First Demo Report");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\softtech\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		//imp method to create method
		extent.flush();
	}

}
