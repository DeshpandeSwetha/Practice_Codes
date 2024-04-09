package testrunners;

import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)

@CucumberOptions(features = "FeatureFiles/adminlogin.feature",glue = "stepdefinitions",
dryRun = false,plugin = {"pretty","html:testreports/loginresult.html"})
public class AdminLoginTest extends AbstractTestNGCucumberTests
{

	
	
}
