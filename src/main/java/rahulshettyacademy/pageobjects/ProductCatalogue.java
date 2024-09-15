package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	//creating a constructor
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;//initializing
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	//using PageFactory
	//hen there is driver element then only we can use page factory
	@FindBy(css=".mb-3")
	List<WebElement> products ;//it was pural so we had to give list
	@FindBy(css=".ng-animating")
	//WebElement spinner ;
	
	
	//when there is no driver element then we need to use By
	By productsBy= By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By Toast = By.cssSelector("#toast-container");
	
	//action method to get the list of products
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	//action method to get the product by name
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod= products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(Toast);
		//waitForElementToDisAppear(spinner);
	}
	

}
