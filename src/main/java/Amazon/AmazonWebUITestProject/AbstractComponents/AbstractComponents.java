package Amazon.AmazonWebUITestProject.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Amazon.AmazonWebUITestProject.pageobjects.CartPage;
import Amazon.AmazonWebUITestProject.pageobjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="*[routerlink*='myorders']")
	WebElement OrdersButton;
	

	public void waitForElementToAppear(By elementLocated) {  // Argument can be-> (By findBy) and then pass this findBy instead of webElement
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(elementLocated));
	}
	public void waitForElementToAppearNotLocator(WebElement element) {  // Argument can be-> (By findBy) and then pass this findBy instead of webElement
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(By elementLocated) {  // Argument can be-> (By findBy) and then pass this findBy instead of webElement
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(elementLocated));
	}
	public void waitForElementToBeClickable(By elementLocated) {  // Argument can be-> (By findBy) and then pass this findBy instead of webElement
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.elementToBeClickable(elementLocated));
	}
	
	
	public CartPage goToCart() {
		cart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrdersPage goToOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		waitForElementToAppearNotLocator(OrdersButton);
		try {OrdersButton.click();
			
		} catch (Exception e) {
			OrdersButton.click();
		}
		OrdersPage ordersPage=new OrdersPage(this.driver);
		return ordersPage;
	}

}  
