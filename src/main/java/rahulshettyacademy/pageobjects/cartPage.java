package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{

	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle ;

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts ;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public checkoutPage goToCheckoutPage()
	{
		checkoutEle.click();
		return new checkoutPage(driver);
		
	}

	
	

}
