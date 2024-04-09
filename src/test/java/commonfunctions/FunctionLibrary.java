package commonfunctions;


import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class FunctionLibrary 
{
	public static WebDriver driver;
	public static Properties conpro;
	//method to launch browser
	public static WebDriver startBrowser() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value not matching",true);
		}
		return driver;
	}
	//method to launch URL
	public static void openUrl(WebDriver driver) throws InterruptedException
	{
		driver.get(conpro.getProperty("Url"));
	Thread.sleep(4000);
	driver.quit();
		
	}
}