package Amazon.AmazonWebUITestProject.pageobjects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> Orders;

	
	public Stream<String> findOrders() {
		Stream<String> OrdersName = Orders.stream().map(n ->n.getText());//.collect(Collectors.toList());
		return OrdersName ;
		
	}
	

}
