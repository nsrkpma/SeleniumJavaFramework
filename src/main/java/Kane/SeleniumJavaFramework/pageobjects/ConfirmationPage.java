package Kane.SeleniumJavaFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".hero-primary")
	private WebElement confirmationMessage;
	
	public String verifyMessage() {
		return confirmationMessage.getText();
	}
	

}
