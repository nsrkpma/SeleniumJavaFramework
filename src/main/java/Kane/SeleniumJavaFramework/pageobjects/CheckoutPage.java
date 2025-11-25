package Kane.SeleniumJavaFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class CheckoutPage extends AbstarctComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Locators ---
    private By countrySelector = By.xpath("//input[@placeholder='Select Country']");
    private By placeOrderButton = By.cssSelector(".action__submit");
    private By suggestionsContainer = By.cssSelector(".ta-results");

    // --- Same Logic ---
    public void selectCountry(String country) {
        driver.findElement(countrySelector).sendKeys(country);
        waitForElementToAppear(suggestionsContainer);

        List<WebElement> suggestions = driver.findElements(By.cssSelector("button span"));
        suggestions.stream()
                .filter(s -> s.getText().equals(country))
                .findFirst()
                .orElseThrow()
                .click();
    }

    public ConfirmationPage placeOrder() {
        driver.findElement(placeOrderButton).click();
        return new ConfirmationPage(driver);
    }
}
