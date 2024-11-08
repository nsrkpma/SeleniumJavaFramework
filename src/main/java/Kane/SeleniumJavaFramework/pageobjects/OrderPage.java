package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class OrderPage extends AbstarctComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderNames;
	
	public boolean verifyOrderPlaced(String productname) {
		return orderNames.stream().anyMatch(orderName-> orderName.getText().equalsIgnoreCase(productname));
		
	}

}
