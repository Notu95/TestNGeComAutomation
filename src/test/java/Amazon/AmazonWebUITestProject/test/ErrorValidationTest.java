package Amazon.AmazonWebUITestProject.test;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;
import Amazon.AmazonWebUITestProject.TestComponent.BaseTest;
import Amazon.AmazonWebUITestProject.pageobjects.CartPage;
import Amazon.AmazonWebUITestProject.pageobjects.ConfirmationPage;
import Amazon.AmazonWebUITestProject.pageobjects.LandingPage;
import Amazon.AmazonWebUITestProject.pageobjects.PlaceOrderPage;
import Amazon.AmazonWebUITestProject.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest {
	
	String userEmail = "randommona1234@gmail.com";
	String pass = "Qwer@123";
	List<String> pListUWant = Arrays.asList("adidas original");// ,"IPHONE 13 PRO");//,"zara coat 3");
	
	
	@Test(groups = {"regression","sanity"})
	public void wrongPasswordTest()  {
		//LogginginErrorValidation
		
		System.out.println(landingPage.validateWrongUserPasswordMsg(userEmail, pass));
//		try {
		Assert.assertEquals("Incorrect email or password.", landingPage.validateWrongUserPasswordMsg(userEmail, pass));
//		}catch(Exception e) {e.printStackTrace();}
		
	}
	@Test(groups = {"regression","sanity"})
	public void wrongUserIdTest()  {
		//LogginginErrorValidation
		System.out.println(landingPage.validateWrongUserPasswordMsg(userEmail, pass));
		Assert.assertEquals("Incorrect email or password.", landingPage.validateWrongUserPasswordMsg(userEmail, pass));
		
	}

}

