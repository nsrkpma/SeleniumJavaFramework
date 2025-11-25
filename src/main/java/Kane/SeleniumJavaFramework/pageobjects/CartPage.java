package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class CartPage extends AbstarctComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Locators ---
    private By cartProducts = By.cssSelector(".cartSection h3");
    private By checkoutButton = By.cssSelector(".totalRow button");
    private By ordersButton = By.xpath("//button[@routerlink='/dashboard/myorders']");

    // --- Same Logic ---
    public Boolean matchproducts(String productname) {
        List<WebElement> products = driver.findElements(cartProducts);
        return products.stream().anyMatch(cartProduct ->
                cartProduct.getText().equals(productname));
    }

    public CheckoutPage goToCheckout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }

    public OrderPage viewOrderDetails() {
        driver.findElement(ordersButton).click();
        return new OrderPage(driver);
    }
}
