package SeleniumMain;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POMActionWebElement.CartSectionPage;
import POMActionWebElement.OrderHistory;
import POMActionWebElement.OrderPage;
import POMActionWebElement.ProductList;
import TestComponent.BaseComponent;


public class SubmitOrder extends BaseComponent
{
	String Item ="IPHONE 13 PRO";
	@Test(dataProvider ="getData" ,groups = {"Purchase"})
	
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException
	//public void SubmitOrder() throws InterruptedException
	{		
		//login   
		ProductList p=lp.Login("anjalirkakade@gmail.com", "Anjali@123");	
		//ProductList p=lp.Login(input.get("Username"), input.get("password"));	
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
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void verifyProductINOrderHistory()
	{
		//String Item ="ZARA COAT 3";
		//login   
		ProductList p=lp.Login("anjalirkakade@gmail.com", "Anjali@123");
		OrderHistory op =lp.goToOrderHome();
		boolean match2=op.VerifyProductInOrderHistory(Item);
		Assert.assertTrue(match2);
	}
	
	//dataprovider
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data =readJSONInputData("C:\\Users\\softtech\\eclipse-workspace\\SeleniumFramework\\src\\main\\java\\Resource\\Input.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}
