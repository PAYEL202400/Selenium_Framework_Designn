package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.cartPage;

public class AbstractComponents {
	WebDriver driver;
	//this is the parent class which contains all the reusability codes
	
	
	
	public AbstractComponents(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	By cart = By.cssSelector("[routerlink*='cart']");
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public cartPage goToCartPage()
	{
		waitForElementToAppear(cart);
		cartHeader.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
		
	}
	public OrderPage goToOrderPage()
	{
		waitForWebElementToAppear(orderHeader);
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
		
	}
	
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException 
	{
		Thread.sleep(1000);
		//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	

}
