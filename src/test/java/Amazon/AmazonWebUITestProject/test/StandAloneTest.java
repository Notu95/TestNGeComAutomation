package Amazon.AmazonWebUITestProject.test;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Amazon.AmazonWebUITestProject.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest { //extends BaseTest
	
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		//ExtentSparkReporter
		String path= System.getProperty("user.dir")+"//Reports//index1.html";
		ExtentSparkReporter reporter =new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("TestResult");
		
		//ExtentReports
		 extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester ", "Sourav");
		
	}
	
	@Test(dataProvider = "getData" ,groups = {"dataProviderEG2"})
	public  void main(HashMap<String, String> map) throws InterruptedException {
		config();
		ExtentTest test=extent.createTest("DemoTest");
//		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\SOURAV\\chrmDriver\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		Actions a = new Actions(driver);

		driver.get("https://rahulshettyacademy.com/client/");
//		String userEmail = "randomsourav123@gmail.com";
//		String pass = "Qwer@123";
		String pUWant = "adidas original";
		List<String> pListUWant = Arrays.asList("adidas original"); // ,"IPHONE 13 PRO","zara coat 3");

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(map.get("email"));
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(map.get("password"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println(driver.findElement(By.id("toast-container")).getText());

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		/*
		 * WebElement prod= products.stream() .filter(n ->
		 * n.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(pUWant))
		 * .findFirst() .orElse(null);
		 */

		for (String p : pListUWant) {
			WebElement prod = products.stream()
					.filter(n -> n.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p)).findFirst()
					.orElse(null);

			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
			w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		}

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProduct = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		cartProduct.stream().forEach(p -> System.out.println(p.getText()));
		pListUWant.stream().forEach(p -> System.out.println(p.toUpperCase()));

		Assert.assertEquals(pListUWant.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()),
				cartProduct.stream().map(n -> n.getText().toUpperCase()).collect(Collectors.toList()));

		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Checkout']"))));
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");

		List<WebElement> options = driver.findElements(By.xpath("//button/span"));
		options.stream().filter(n -> n.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".actions a"))));
		System.out.println(driver.findElement(By.cssSelector(".actions a")).getText());

		Thread.sleep(1000L);

		a.moveToElement(driver.findElement(By.cssSelector(".actions a"))).click().build().perform();

		//test.fail("mis-match report");
		extent.flush();	

	}
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "randomsourav123@gmail.com");
//		map1.put("password", "Qwer@123");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "randommona1234@gmail.com");
//		map2.put("password", "Qwer@1234");
		BaseTest bt =new BaseTest();
		List<HashMap<String, String>> data= bt.getJsonDataToHashMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\Amazon\\AmazonWebUITestProject\\data\\LoginToOrder.json");
		
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	
	
}
