package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;//assigning a global variable
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		//in java there is a properties class through which we can decide on which browser we want to execute our 
		//test and to access that we need to define global properties
		
		Properties prop = new Properties();
		//how we can store the file containing global properties as input stream-by creating object for fileinputstream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browserName= prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
	    driver= new ChromeDriver();
		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox;
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//first we are reading json file to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	//String to Hashmap jackson databind
				
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
	    landingpage= new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
    }
	@AfterMethod(alwaysRun = true)
	public void shutDown()
	{
		driver.close();
	}

}
