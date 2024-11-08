package Kane.SeleniumJavaFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Kane.SeleniumJavaFramework.TestComponents.BaseTest;
import Kane.SeleniumJavaFramework.TestComponents.Retry;
import Kane.SeleniumJavaFramework.pageobjects.CartPage;
import Kane.SeleniumJavaFramework.pageobjects.CheckoutPage;
import Kane.SeleniumJavaFramework.pageobjects.ConfirmationPage;
import Kane.SeleniumJavaFramework.pageobjects.OrderPage;
import Kane.SeleniumJavaFramework.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	private static final boolean flase = false;
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getDataMap",retryAnalyzer=Retry.class)
	public void endToendTest(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));
		productname = input.get("productname");
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productname);
		productCatalogue.addProductToCart(productname);

		CartPage cartpage = productCatalogue.goToCart();
		boolean match = cartpage.matchproducts(productname);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutpage.placeOrder();
		Assert.assertEquals(confirmationPage.verifyMessage(), "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = { "endToendTest" }, enabled = flase)
	public void orderHistory() {
		ProductCatalogue productCatalogue = landingPage.login("Kane@gmail.com", "Kane@1213");
		OrderPage orderPage = productCatalogue.viewOrderDetails();
		Assert.assertTrue(orderPage.verifyOrderPlaced(productname));
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Kane@gmail.com", "Kane@1213", "ZARA COAT 3" },
				{ "Kane@gmail.com", "Kane@1213", "ADIDAS ORIGINAL" } };
	}

	@DataProvider
	public Object[][] getDataMap() throws IOException {
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("email", "Kane@gmail.com");
//			map.put("password", "Kane@1213");
//			map.put("productname", "ZARA COAT 3");
//			HashMap<String,String> map1 = new HashMap<String,String>();
//			map.put("email", "Kane@gmail.com");
//			map.put("password", "Kane@1213");
//			map.put("productname", "ADIDAS ORIGINAL");
//			return new Object[][] {{map},{map1}};
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\Kane\\SeleniumJavaFramework\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) }, { data.get(3) } };

	}

}
