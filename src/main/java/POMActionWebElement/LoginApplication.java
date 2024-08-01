package POMActionWebElement;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstactComponant.Abstract;


public class LoginApplication extends Abstract
{
	WebDriver driver;
	
	public LoginApplication(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[id='userEmail']")
	WebElement user;
	
	@FindBy(css="[id='userPassword']")
	WebElement password;
	
	@FindBy(css="[id='login']")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	public ProductList Login(String username, String pass)
	{
		user.sendKeys(username);
		password.sendKeys(pass);
		loginButton.click();
		ProductList p = new ProductList(driver);
		return p;
		
	}
	public void goToLink()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		 return errormessage.getText();
	}
}
