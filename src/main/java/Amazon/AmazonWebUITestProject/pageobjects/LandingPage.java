package Amazon.AmazonWebUITestProject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AmazonWebUITestProject.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);  //sending driver object to parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // It will initialize all the webElements when constructor is called
	}

	//WebElement UserIDField= driver.findElement(By.xpath("//input[@type='email']"));
	//PageFactory
	@FindBy(xpath="//input[@type='email']")
	WebElement UserIDField;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement PasswordField;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement logInButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement logInToast;
	
	By logInToastBy=By.cssSelector("*[class*='flyInOut']");
	
	public ProductCatalogue loginApp(String userEmail,String pass) {
		UserIDField.sendKeys(userEmail);
		PasswordField.sendKeys(pass);
		logInButton.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo(String url) {
		driver.get(url);
		
	}
	public String validateWrongUserPasswordMsg(String userEmail,String pass) {
		UserIDField.clear();
		UserIDField.sendKeys(userEmail);
		PasswordField.clear();
		PasswordField.sendKeys(pass);
		logInButton.click();
		waitForElementToAppear(logInToastBy);
		String errorMsg=logInToast.getText();
		return errorMsg;
	}
}
