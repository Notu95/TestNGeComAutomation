package Amazon.AmazonWebUITestProject.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;
import Amazon.AmazonWebUITestProject.TestComponent.BaseTest;
import Amazon.AmazonWebUITestProject.pageobjects.CartPage;
import Amazon.AmazonWebUITestProject.pageobjects.ConfirmationPage;
import Amazon.AmazonWebUITestProject.pageobjects.LandingPage;
import Amazon.AmazonWebUITestProject.pageobjects.OrdersPage;
import Amazon.AmazonWebUITestProject.pageobjects.PlaceOrderPage;
import Amazon.AmazonWebUITestProject.pageobjects.ProductCatalogue;
import Amazon.AmazonWebUITestProject.data.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginToOrderTest extends BaseTest {

	ExcelDataReader edr=new ExcelDataReader();
	String pUWant = "adidas original";
	String country = "India";
	//List<String> pListUWant = Arrays.asList("adidas original");// ,"IPHONE 13 PRO");//,"zara coat 3");
	
	List<String> pListUWant;
	CartPage cartPage;

	@Test(dataProvider = "getData", groups = { "regression", "sanity", "dataProviderEG" })
	public void validateCartItemsTest(HashMap<String, String> map) throws IOException {
		// LaunchApp--> replaced by @BeforeTest
		// LandingPage landingpage=launchApp(url);

		// Loggingin
		ProductCatalogue productCatalogue = landingPage.loginApp(map.get("email"), map.get("password"));

		// collecting product catalogue
		List<WebElement> products = productCatalogue.getProductList();

		// adding product to cart as per list
		List<String>l=edr.getDataForLogintoOrderTest();
		System.out.println(l);
		l.remove(0);
		pListUWant=l;
		System.out.println(pListUWant);		
		productCatalogue.addToCart(pListUWant);

		// goToCart
		cartPage = productCatalogue.goToCart();

		// Verify product display
		List<String> wrong=Arrays.asList("abc");
		boolean flag = cartPage.verifyCartproductDisplay(pListUWant);    //(wrong);//(pListUWant);
		Assert.assertTrue(flag);

		System.out.println("Hi, the end1");

	}

	@Test(dependsOnMethods = "validateCartItemsTest", groups = { "regression", "sanity" })
	public void validatePlaceOrderTest() throws InterruptedException {

		// checkOut
		PlaceOrderPage placeOrderPage = cartPage.checkOut();

		// Payment and Order place
		placeOrderPage.selectCountry(country);
		ConfirmationPage confirmationPage = placeOrderPage.placeOrder();

		// Confirmation verification
		System.out.println(confirmationPage.getOrderId());
		System.out.println("Hi, the end2");

	}

	@Test(dependsOnMethods = "validatePlaceOrderTest", groups = { "regression" })
	public void validateOrders() {
		OrdersPage ordersPage = goToOrders();
		System.out.println(ordersPage.findOrders().collect(Collectors.toList()));
		Assert.assertTrue(ordersPage.findOrders().allMatch(pListUWant::contains));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "randomsourav123@gmail.com");
		map1.put("password", "Qwer@123");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "randommona1234@gmail.com");
//		map2.put("password", "Qwer@1234");
		/* To check data coming from json comment in the below */
		/* List<HashMap<String, String>> data= getJsonDataToHashMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\Amazon\\AmazonWebUITestProject\\data\\LoginToOrder.json");
		
		return new Object[][] { { data.get(0) }, { data.get(1) } }; */
		
		return new Object[][] { { map1 } };

	}

}
