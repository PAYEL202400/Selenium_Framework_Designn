package rahulshettyacademy.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	//creating a constructor
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;//initializing
		PageFactory.initElements(driver, this);
	}
	
	//WebElement UserEmail= driver.findElement(By.id("userEmail"));
	//using PageFactory
	
	@FindBy(id="userEmail")
	WebElement emailEle;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement passwordEle;
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	//By error = By.cssSelector("[class*='flyInOut']");
	
	public ProductCatalogue LoginApplication(String email, String password)
	{
		emailEle.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
