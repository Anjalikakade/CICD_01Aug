package POMActionWebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstactComponant.Abstract;

public class OrderHistory extends Abstract
{
	WebDriver driver;
	
	public OrderHistory(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr//td[2]")
	List<WebElement> orderproductListcart;

	
	//verify productonOrder Section
	public boolean VerifyProductInOrderHistory(String Item)
	{
		
		boolean match =orderproductListcart.stream().anyMatch(p->p.getText().equalsIgnoreCase(Item));
		return match ;
	}
}
