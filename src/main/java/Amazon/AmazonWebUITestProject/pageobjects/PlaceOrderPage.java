package Amazon.AmazonWebUITestProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents{
	
	WebDriver driver;
	Actions a;
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);  //sending driver object to parent class
		this.driver=driver;
		this.a = new Actions(driver);
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
		
	
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button/span")
	List<WebElement> CountryOptions;
	
	
	By placeOrderButton=By.cssSelector(".actions a");
	By CountryOptionsBy=By.xpath("//input[@placeholder='Select Country']");
	
	public void selectCountry(String country) {
		Country.click();
		Country.sendKeys(country);
		waitForElementToAppear(CountryOptionsBy);
		CountryOptions.stream().filter(n -> n.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		
	}
	
	
	public ConfirmationPage placeOrder() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		waitForElementToAppear(placeOrderButton);
		Thread.sleep(1000L);
		a.moveToElement(driver.findElement(By.cssSelector(".actions a"))).click().build().perform();
		
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
		
		//System.out.println(driver.findElement(By.cssSelector(".actions a")).getText());

		
	}

}
