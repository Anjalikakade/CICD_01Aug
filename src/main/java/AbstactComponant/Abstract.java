package AbstactComponant;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POMActionWebElement.CartSectionPage;
import POMActionWebElement.OrderHistory;

public class Abstract 
{
	WebDriver driver;
	
	public Abstract(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement Cart; 
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement order;
	
	public void waitTillAppear(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));
	}
	
	public void waitTillDisAppear(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
	}
	public CartSectionPage goToCartHome()
	{
		Cart.click();
		CartSectionPage c = new CartSectionPage(driver);
		return c;
	}
	
	public OrderHistory goToOrderHome()
	{
		order.click();
		OrderHistory op = new OrderHistory(driver);
		return op;
	}
}
