package TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import POMActionWebElement.LoginApplication;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseComponent 
{
	public WebDriver driver;
	public LoginApplication lp;

	public WebDriver initializeDriver() throws IOException
	{
		//create property class to read value from global properties file
		
		Properties prod = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\softtech\\eclipse-workspace\\SeleniumFramework\\src\\main\\java\\Resource\\global.properties");
		
		prod.load(file);
		//read browser value from both terminal variable or property file
		String bname = System.getProperty("browser")!=null ?System.getProperty("browser"):prod.getProperty("browser");
		//String bname =prod.getProperty("browser");
		System.out.println(bname);
		if(bname.contains("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\softtech\\chromedriver-win64\\chromedriver.exe");
			if(bname.contains("headless"))
			{
			 options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400, 900));
		}
		else if (bname.equalsIgnoreCase("edge"))
		{
			//code for edge
			System.getProperty("webdriver.edge.driver","C:\\Users\\softtech\\EdgeDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else if(bname.equalsIgnoreCase("fireFox"))
		{
			//code for firefox
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginApplication lounchApplication() throws IOException
	{
		driver = initializeDriver();
		System.out.println("Driver Life:" + driver);
		lp = new LoginApplication(driver);
		lp.goToLink();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void CloseBrowser()
	{
		driver.close();
	}
	
	//read input data from json file
	public List<HashMap<String, String>> readJSONInputData(String file) throws IOException
	{
		//read .json data in string
		String json =FileUtils.readFileToString(new File(file), StandardCharsets.UTF_8);
		
		//convert string data into hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(json, new TypeReference <List<HashMap<String, String>>>(){
		});
		return data;
		
	}
	
	//take screenshot
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File Source =ts.getScreenshotAs(OutputType.FILE);
		File filepath = new File("C:\\Users\\softtech\\eclipse-workspace\\SeleniumFramework\\Reports" +testCaseName +".png");
		FileUtils.copyFile(Source,filepath );
		return "C:\\Users\\softtech\\eclipse-workspace\\PractiseSelenium\\Reports" +testCaseName +".png";
	}
	
}
