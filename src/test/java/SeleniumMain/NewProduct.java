package SeleniumMain;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POMActionWebElement.CartSectionPage;
import POMActionWebElement.LoginApplication;
import POMActionWebElement.OrderHistory;
import POMActionWebElement.OrderPage;
import POMActionWebElement.ProductList;
import TestComponent.BaseComponent;


public class NewProduct 
{
	String Item ="IPHONE 13 PRO";
	@Test
	public void SubmitOrder() throws InterruptedException
	{		
		//login 
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\softtech\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		LoginApplication lp = new LoginApplication(driver);
		ProductList p=lp.Login("anjalirkakade@gmail.com", "Anjali@123");	
		//add item 
		p.AddItemtoCart(Item);
		
		System.out.println("+++++++");
		//verify Item in cart Section 
		CartSectionPage c =p.goToCartHome();
		boolean match =c.VerifyProductOnCartSection(Item);
		assertTrue(match);
		System.out.println("Added Item :" +Item + ":" +match);
		c.clickCheckout();
		c.SelectCountry("India");
		
		OrderPage o = c.PlaceOrder();
		Thread.sleep(5000);
		String value =o.getTitle();
		System.out.println("Successfully:"+ value);
		Assert.assertTrue(value.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		
		
		
	}
	
}
