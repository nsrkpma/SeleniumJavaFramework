package Kane.SeleniumJavaFramework.pageobjects;

import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Locators ---
    private By confirmationMessage = By.cssSelector(".hero-primary");

    // --- Same Logic ---
    public String verifyMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
