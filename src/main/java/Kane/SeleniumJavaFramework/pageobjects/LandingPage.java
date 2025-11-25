package Kane.SeleniumJavaFramework.pageobjects;

import org.openqa.selenium.*;
import Kane.SeleniumJavaFramework.AbstractComponents.AbstarctComponent;

public class LandingPage extends AbstarctComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // --- Updated: Using By locators instead of @FindBy ---
    private By userEmail = By.id("userEmail");
    private By userPassword = By.id("userPassword");
    private By loginButton = By.id("login");
    private By errorToast = By.id("toast-container");
    private By emailErrorMessage = By.xpath("//div[contains(text(),'Enter Valid Email')]");

    // --- SAME LOGIC as your original login() ---
    public ProductCatalogue login(String username, String password) {
        driver.findElement(userEmail).sendKeys(username);
        driver.findElement(userPassword).sendKeys(password);
        driver.findElement(loginButton).click();
        return new ProductCatalogue(driver);
    }

    // --- SAME LOGIC ---
    public void goToUrl(String url) {
        driver.get(url);
    }

    // --- SAME LOGIC, optimized for Selenium 4 ---
    public String getErrorToast() {
        waitForElementToAppear(errorToast);
        return driver.findElement(errorToast).getText();
    }

    // --- SAME LOGIC ---
    public boolean verifyEmailErrorToast() {
        return driver.findElement(emailErrorMessage).isDisplayed();
    }
}
