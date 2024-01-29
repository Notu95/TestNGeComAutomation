package Amazon.AmazonWebUITestProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
	
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productsAt=By.cssSelector(".mb-3");
	By toastMsgAt=By.cssSelector("#toast-container");
	By loadingCircleAt=By.cssSelector(".ngx-spinner-overlay");
	
	
	public List getProductList() {
		waitForElementToAppear(productsAt);
		return products;
	}

	public void addToCart(List<String> pListUWant) {
		for (String p : pListUWant) {
			WebElement prod = products.stream()
					.filter(n -> n.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p)).findFirst()
					.orElse(null);

			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			waitForElementToAppear(toastMsgAt);
			waitForElementToDisappear(loadingCircleAt);
			
		}
		
	}

}
