package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class ProductCatalogue extends AbstarctComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;
	

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productname) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productname) throws InterruptedException {
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmessage);
		Thread.sleep(1000);
		//waitForElementToDisappear(spinner);
	}
	
	public CartPage goToCart() {
		cartIcon.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersButton;
	
	public OrderPage viewOrderDetails() {
		OrdersButton.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	

}
