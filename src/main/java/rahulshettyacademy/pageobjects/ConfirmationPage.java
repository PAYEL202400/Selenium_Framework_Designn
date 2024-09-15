package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".hero-primary")
	WebElement confirmMessage ;
	
	public String getConfirmationMessage()
	{
		return confirmMessage.getText();
	}

}
