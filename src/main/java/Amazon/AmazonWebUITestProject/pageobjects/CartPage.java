package Amazon.AmazonWebUITestProject.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;


import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
	
	}
	
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProduct;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutbutton;
	
	
	By checkOutAt=By.xpath("//button[text()='Checkout']");
	
	
	public boolean verifyCartproductDisplay(List<String> pListUWant) {
		List<String> pListUWant1 =pListUWant.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
		List<String> cartProductList=cartProduct.stream().map(n -> n.getText().toUpperCase()).collect(Collectors.toList());
		boolean flag= pListUWant1.equals(cartProductList);
				
		cartProduct.stream().forEach(p -> System.out.println(p.getText()));
		pListUWant.stream().forEach(p -> System.out.println(p.toUpperCase()));
		
		return flag;
	}
	public PlaceOrderPage checkOut() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		//js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
		waitForElementToAppear(checkOutAt);
		waitForElementToBeClickable(checkOutAt );
		checkOutbutton.click();
		PlaceOrderPage  placeOrderPage= new PlaceOrderPage(driver);
		return placeOrderPage;
	}

	

}
