package rahulshettyacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;



public class ErrorValidationsTest extends BaseTest{
		
	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException{
		
		//String productName="ZARA COAT 3";
		
		landingpage.LoginApplication("payelbhadury2000@gmail.com", "Paysaa@2024#");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException{
		
		String productName="ZARA COAT 3";
		
		ProductCatalogue productCatalogue=landingpage.LoginApplication("sajalbhadury@gmail.com", "Paysajma@2024#");
		List<WebElement> products=productCatalogue.getProductList();		
		productCatalogue.addProductToCart(productName);
		cartPage cartpage = productCatalogue.goToCartPage();
		Boolean match= cartpage.VerifyProductDisplay("ZARA COAT 33");//product name coming for test data
		Assert.assertFalse(match);//validation cannot go in POM, as validations should be inside test case only
	}
	
}
