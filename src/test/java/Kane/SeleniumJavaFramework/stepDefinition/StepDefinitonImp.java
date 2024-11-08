package Kane.SeleniumJavaFramework.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Kane.SeleniumJavaFramework.TestComponents.BaseTest;
import Kane.SeleniumJavaFramework.pageobjects.CartPage;
import Kane.SeleniumJavaFramework.pageobjects.CheckoutPage;
import Kane.SeleniumJavaFramework.pageobjects.ConfirmationPage;
import Kane.SeleniumJavaFramework.pageobjects.LandingPage;
import Kane.SeleniumJavaFramework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitonImp extends BaseTest{

	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CartPage cartpage;
	ConfirmationPage confirmationPage ;
	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username , String password)
	{
		productCatalogue = landingPage.login(username,password);
	}
	
	@When("^I add the product (.+) to the cart$")
	public void i_add_product_to_cart(String productname) throws InterruptedException {
		productCatalogue.addProductToCart(productname);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_sublit_order(String productname) {
		
		cartpage = productCatalogue.goToCart();
		boolean match = cartpage.matchproducts(productname);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationPage = checkoutpage.placeOrder();
	}
	
	@Then("{string} messgae is displayed on cinfirmationPage")
	public void message_dispalyed_on_confirmation_page(String string){
		Assert.assertEquals(confirmationPage.verifyMessage(),string);
		driver.close();
	}
	
	@Then("{string} messgae is displayed")
	public void error_message_dispalyed_on_confirmation_page(String string){
		Assert.assertEquals(landingPage.getErrorToast(),string);
		driver.close();
	}
	
	
	
}
