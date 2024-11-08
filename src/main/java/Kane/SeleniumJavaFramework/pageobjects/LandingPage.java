package Kane.SeleniumJavaFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class LandingPage extends AbstarctComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement userPassword;

	// page factory
	@FindBy(id = "login")
	private WebElement loginButton;
	
	@FindBy(id="toast-container")
	private WebElement errorToast;
	
	@FindBy(xpath="//div[contains(text(),'Enter Valid Email')]")
	private WebElement emailErrorMessage;

	public ProductCatalogue login(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goToUrl(String url) {
		driver.get(url);
	}
	
	public String getErrorToast() {
		waitForWebElementToAppear(errorToast);
		return errorToast.getText();
	}
	
	public boolean verifyEmailErrorToast() {
		return emailErrorMessage.isDisplayed();
	}

}
