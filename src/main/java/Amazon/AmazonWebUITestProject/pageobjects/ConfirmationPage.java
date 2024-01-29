package Amazon.AmazonWebUITestProject.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);  //sending driver object to parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
	}
	
	@FindBy(css="label.ng-star-inserted")
	List<WebElement> orderId;
	
	public List getOrderId() {
		List<String>orderIds=orderId.stream().map(n ->n.getText()).map(s -> s.substring(1, (s.length()-1)).trim()).collect(Collectors.toList());
		return orderIds;
		
	}
	
	

}
