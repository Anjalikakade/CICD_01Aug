package CucumberStepDefination;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;

import POMActionWebElement.CartSectionPage;
import POMActionWebElement.LoginApplication;
import POMActionWebElement.OrderPage;
import POMActionWebElement.ProductList;
import TestComponent.BaseComponent;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class cucucmberStepDefination extends BaseComponent
{
	public LoginApplication lp ;
	public ProductList p;
	public CartSectionPage c;
	public OrderPage o;
	@Given("I landed on Eccomorce Page")
	public void land_on_webpage() throws IOException
	{
		lp=lounchApplication();
	}

	
	@Given("^Login with (.+) and (.+)$")
	public void login_Application(String user,String pass)
	{
		p=lp.Login(user, pass);
	}
	@When("^I Add product (.+) on cart list$")
	public void add_product_cart_list(String Item)
	{
		p.AddItemtoCart(Item);
		
		System.out.println("+++++++");
	}
	
	@When("^checkout (.+) and Submit the Order$")
	public void checkout_SubmitOrder(String Item) throws InterruptedException
	{
		//verify Item in cart Section 
				c =p.goToCartHome();
				boolean match =c.VerifyProductOnCartSection(Item);
				assertTrue(match);
				System.out.println("Added Item :" +Item + ":" +match);
				c.clickCheckout();
				c.SelectCountry("India");		
				o = c.PlaceOrder();
				Thread.sleep(5000);
		
	}
	 @Then("{string} meassage is displayed on confirmation page")
	 public void Verify_Success_message(String string)
	 {
		  String value =o.getTitle();
		  System.out.println("Successfully:"+ value);
		  Assert.assertTrue(value.equalsIgnoreCase(string));
		  driver.close();
	 }
	 
	 
	 @Then("{string} meassage is displayed on login page")
	 public void verify_Error_on_login_page(String str)
	 {
		 String error =lp.getErrorMessage();
		 System.out.println(error);
		 Assert.assertEquals(str, lp.getErrorMessage());
		 driver.close();
		 
	 }
}
