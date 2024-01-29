package Amazon.AmazonWebUITestProject.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;
import Amazon.AmazonWebUITestProject.pageobjects.LandingPage;
import Amazon.AmazonWebUITestProject.pageobjects.OrdersPage;
import io.github.bonigarcia.wdm.*; //WebDriverManager.*;

public class BaseTest {
	protected WebDriver driver;
	protected LandingPage landingPage;
	protected OrdersPage ordersPage;
	String url="https://rahulshettyacademy.com/client/";
	
	public WebDriver initializeDriver() throws IOException {
		//property class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
						"\\src\\main\\java\\Amazon\\AmazonWebUITestProject\\resources\\GlobalData.properties");  
		//FileInputStream fis=new FileInputStream("C:\\Users\\SOURAV\\OneDrive\\Desktop\\JAVA_Projects\\eclipse-workspace\\AmazonWebUITestProject\\src\\main\\java\\Amazon\\AmazonWebUITestProject\\resources\\GlobalData.properties");
		prop.load(fis);
		//String browserName=prop.getProperty("browser");
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		System.out.println(browserName);
		
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		options.addArguments("headless");}
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\SOURAV\\chrmDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver(options);
		
		}
		else if(browserName=="firefox") {
			//fireFox
		}
		else if(browserName=="edge") {
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1440,900));
		
		return driver;
		
	}
public  List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException{
		
		//Reading JSON to String
		String JSONContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		//String To HashMap Jackson Databind --> add the dependency
		ObjectMapper mapper=new ObjectMapper();
		// List<HashMap<String, String>> data = mapper.readValue(JSONContent, new TypeReference<List<HashMap<String, String>>>() {});
		List<HashMap<String,String>> data=mapper.readValue(JSONContent,new TypeReference<List<HashMap<String,String>>>(){} );
		return data;
		
		
	}
	
	@BeforeMethod(alwaysRun = true)
	//@BeforeTest(alwaysRun = true)		
	public  LandingPage launchApp() throws IOException {
		driver= initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo(url);
		return landingPage;
	}
	
	@AfterTest(alwaysRun = true)
	public void closeApp() {
		//driver.quit();
	}
	
	public OrdersPage goToOrders() {
		AbstractComponents abstractComponents =new AbstractComponents(this.driver);
		return ordersPage =abstractComponents.goToOrder();
	}
	public String takeScreenshot(String TCName, WebDriver driver) throws IOException {
		TakesScreenshot tc=(TakesScreenshot)driver;
		File source=tc.getScreenshotAs(OutputType.FILE);
		File destinaton= new File(System.getProperty("user.dir")+"//Reports//"+TCName+".png");
		FileUtils.copyFile(source,destinaton );
		return System.getProperty("user.dir")+"//rports//"+TCName+".png";
	}
	

}
