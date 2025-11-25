package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class ProductCatalogue extends AbstarctComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Locators ---
    private By products = By.cssSelector(".mb-3");
    private By spinner = By.cssSelector(".ng-animating");
    private By cartIcon = By.xpath("//button[@routerlink='/dashboard/cart']");
    private By addToCart = By.cssSelector(".card-body button:last-of-type");
    private By toastmessage = By.cssSelector("#toast-container");
    private By ordersButton = By.xpath("//button[@routerlink='/dashboard/myorders']");

    // --- Same Logic ---
    public List<WebElement> getProductList() {
        waitForElementToAppear(products);
        return driver.findElements(products);
    }

    public WebElement getProductByName(String productname) {
        return getProductList().stream()
                .filter(product -> product.findElement(By.tagName("b"))
                        .getText().equals(productname))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(String productname) throws InterruptedException {
        WebElement prod = getProductByName(productname);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastmessage);
        Thread.sleep(1000); // Keeping logic same
    }

    public CartPage goToCart() {
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }

    public OrderPage viewOrderDetails() {
        driver.findElement(ordersButton).click();
        return new OrderPage(driver);
    }
}
