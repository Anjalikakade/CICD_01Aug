package SeleniumMain;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import POMActionWebElement.CartSectionPage;
import POMActionWebElement.ProductList;
import TestComponent.BaseComponent;
import TestComponent.RetryAnalyzer;


public class ErrorValidationLogin extends BaseComponent
{
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void LoginErrorValidation()
	{
		String Item ="ZARA COAT 3";
		//login   
		ProductList p=lp.Login("anjalirkakade@gmail.com", "Anjali@12334");
		String error =lp.getErrorMessage();
		System.out.println(error);
		Assert.assertEquals("Incorrect email  password.", lp.getErrorMessage()); //Incorrect email or password.
		   
	}
	@Test
	public void ProductErrorValidation()
	{
		String Item ="ZARA COAT 3";
		//login   
		ProductList p=lp.Login("anjalirkakade@gmail.com", "Anjali@123");	
		
		//add item 
		p.AddItemtoCart(Item);
		
		//verify Item in cart Section
		CartSectionPage c =p.goToCartHome();
		boolean match =c.VerifyProductOnCartSection("ZARA COAT 33");
		assertFalse(match);
	}
	

}
