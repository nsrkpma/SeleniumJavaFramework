package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class OrderPage extends AbstarctComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Locators ---
    private By orderNames = By.xpath("//tr/td[2]");

    // --- Same Logic ---
    public boolean verifyOrderPlaced(String productname) {
        List<WebElement> names = driver.findElements(orderNames);
        return names.stream()
                .anyMatch(orderName -> orderName.getText().equalsIgnoreCase(productname));
    }
}
