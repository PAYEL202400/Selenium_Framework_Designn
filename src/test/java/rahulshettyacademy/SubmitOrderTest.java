package rahulshettyacademy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;



public class SubmitOrderTest extends BaseTest{
	//String productName="ZARA COAT 3";
	
	private String productName;

	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException{
		
	  
	ProductCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();		
		productCatalogue.addProductToCart(input.get("product"));
		cartPage cartpage = productCatalogue.goToCartPage();
		Boolean match= cartpage.VerifyProductDisplay(input.get("product"));//product name coming for test data
		Assert.assertTrue(match);//validation cannot go in POM, as validations should be inside test case only
	
		checkoutPage checkoutpage= cartpage.goToCheckoutPage();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderHistoryTest()
	{
		
		ProductCatalogue productCatalogue=landingpage.LoginApplication("payelbhadury2000@gmail.com", "Paysajma@2024#");
		OrderPage orderpage= productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*HashMap<String,String> map = new HashMap<String,String>();
	map.put("email", "payelbhadury2000@gmail.com");
	map.put("password", "Paysajma@2024#");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
	map1.put("email", "sajalbhadury@gmail.com");
	map1.put("password", "Paysajma@2024#");
	map1.put("product", "ADIDAS ORIGINAL");
	
	return new Object[][] {{map},{map1}};*/
	

}
