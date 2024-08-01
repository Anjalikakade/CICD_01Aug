package POMActionWebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstactComponant.Abstract;

public class ProductList extends Abstract
{
	WebDriver driver;
	
	public ProductList(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3") //div[contains(@class,'col-lg-4')]//h5/b
	List<WebElement> productlist;
	
	By producBy = By.className(".mb-3");
	
	By addtocart =By.cssSelector(".card-body button:last-of-type"); //button[@class='btn w-10 rounded']
	By toastMessage=By.id("toast-container");
	
	public List<WebElement> getProductList()
	{
		waitTillAppear(By.cssSelector(".col-lg-4"));   // 
		return productlist;
	}
	
	public WebElement getItemByName(String Item)
	{
		WebElement product = getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(Item)).findAny().orElse(null);
		System.out.println("productname:" + product);  // //div[contains(@class,'col-lg-4')]//h5/b
		return product;
	}
	
	public void AddItemtoCart(String Item)
	{
		WebElement product = getItemByName(Item);
		product.findElement(addtocart).click();
		waitTillAppear(toastMessage);
		waitTillDisAppear(toastMessage);
	}

}
