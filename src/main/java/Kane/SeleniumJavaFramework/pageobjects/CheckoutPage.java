package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class CheckoutPage extends AbstarctComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement countrySelector;

	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	
	By suggestionsby= By.cssSelector(".ta-results");

	public void selectCountry(String country) {
		
		countrySelector.sendKeys(country);
		waitForElementToAppear(suggestionsby);
		List<WebElement>  suggestions=driver.findElements(By.cssSelector("button span"));
		
	
		suggestions.stream().filter(suggestion->
		suggestion.getText().equals(country)).findFirst().orElse(null).click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
	

}
