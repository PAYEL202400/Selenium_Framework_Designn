package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class checkoutPage extends AbstractComponents{

	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing
		PageFactory.initElements(driver, this);
		
	}
	
	//page factory elements
	@FindBy(css=".action__submit")
	WebElement submit ;
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country ;
	
	//(//button[contains(@class,'ta-item')])[2]"
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry ;
	
	By Results= By.cssSelector(".ta-results");//no webelemnent here.
	By submitbtn= By.cssSelector(".action__submit");
	
	//based upon the pagefactory elements we need to write actions
	public void selectCountry(String CountryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(Results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		waitForElementToAppear(submitbtn);
		submit.click();
		return new ConfirmationPage(driver);
	}
	

}
