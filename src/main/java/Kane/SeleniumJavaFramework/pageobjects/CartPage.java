package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class CartPage extends AbstarctComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	private WebElement checkoutButton;
	
	public Boolean matchproducts(String productname) {
		Boolean match=cartProducts.stream().anyMatch(cartProduct->
		cartProduct.getText().equals(productname));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		CheckoutPage checkoutpage=new CheckoutPage(driver);
		return checkoutpage;
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersButton;
	
	public OrderPage viewOrderDetails() {
		OrdersButton.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
}
