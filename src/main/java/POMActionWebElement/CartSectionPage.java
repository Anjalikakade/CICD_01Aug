package POMActionWebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstactComponant.Abstract;

public class CartSectionPage extends Abstract
{
WebDriver driver;
	
	public CartSectionPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By title = By.xpath("//div[@class='cartSection']//h3");
	By cartlist = By.xpath("//div[@class='cartSection']");
	
	@FindBy(xpath="//div[@class='cartSection']")
	List<WebElement> productListcart;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutbtn;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement inputCountry;
	
	@FindBy(xpath="(//button[contains(@class,'list-group')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public List<WebElement> getCartProductList()
	{
		waitTillAppear(cartlist);
		return productListcart;
	}
	//verify productoncart Section
	public boolean VerifyProductOnCartSection(String Item)
	{
		
		boolean match =getCartProductList().stream().anyMatch(p->p.findElement(title).getText().equalsIgnoreCase(Item));
		return match ;
	}

	//click on checkout button
	public void clickCheckout()
	{
		checkoutbtn.click();
	}
	
	//select country
	public void SelectCountry(String cname) 
	{
		Actions a = new Actions(driver);
		a.sendKeys(inputCountry,cname).build().perform();
		waitTillAppear(By.xpath("//section[contains(@class,'ta-results')]"));
		selectCountry.click();
		
		
	}
	
	public OrderPage PlaceOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1200)","");
		Thread.sleep(3000);
		submit.click();
		OrderPage o = new OrderPage(driver);
		return o;
	}
	
}
