package Kane.SeleniumJavaFramework.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Kane.SeleniumJavaFramework.TestComponents.BaseTest;
import Kane.SeleniumJavaFramework.TestComponents.Retry;
import Kane.SeleniumJavaFramework.data.ExcelReader;
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

	/*
	 * @DataProvider public Object[][] getData() { return new Object[][] { {
	 * "Kane@gmail.com", "Kane@1213", "ZARA COAT 3" }, { "Kane@gmail.com",
	 * "Kane@1213", "ADIDAS ORIGINAL" } }; }
	 */

	/*
	 * @DataProvider public Object[][] getDataMap() throws IOException { //
	 * HashMap<String,String> map = new HashMap<String,String>(); //
	 * map.put("email", "Kane@gmail.com"); // map.put("password", "Kane@1213"); //
	 * map.put("productname", "ZARA COAT 3"); // HashMap<String,String> map1 = new
	 * HashMap<String,String>(); // map.put("email", "Kane@gmail.com"); //
	 * map.put("password", "Kane@1213"); // map.put("productname",
	 * "ADIDAS ORIGINAL"); // return new Object[][] {{map},{map1}};
	 * List<HashMap<String, String>> data =
	 * getJsonDataToMap(System.getProperty("user.dir") +
	 * "\\src\\test\\java\\Kane\\SeleniumJavaFramework\\data\\PurchaseOrder.json");
	 * return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) }, {
	 * data.get(3) } };
	 * 
	 * }
	 */
	
	@DataProvider
	public Object[][] getDataMap() throws IOException {

	    String excelPath = System.getProperty("user.dir")
	            + File.separator + "src"
	            + File.separator + "test"
	            + File.separator + "java"
	            + File.separator + "Kane"
	            + File.separator + "SeleniumJavaFramework"
	            + File.separator + "data"
	            + File.separator + "PurchaseOrder.xlsx";

	    ExcelReader excelReader = new ExcelReader();

	    List<HashMap<String, String>> data =
	            excelReader.getExcelData(excelPath, "Sheet1");

	    // ---------- PRINT DATA TO CONSOLE ----------
	    System.out.println("===== Excel Data Loaded From Sheet1 =====");
	    for (int row = 0; row < data.size(); row++) {

	        HashMap<String, String> map = data.get(row);

	        System.out.println("Row " + (row + 1) + " data:");

	        for (String key : map.keySet()) {
	            System.out.println("   " + key + " = " + map.get(key));
	        }

	        System.out.println("----------------------------------------");
	    }
	    System.out.println("==========================================");

	    // ---------- RETURN DATA TO TEST ----------
	    Object[][] result = new Object[data.size()][1];

	    for (int i = 0; i < data.size(); i++) {
	        result[i][0] = data.get(i);
	    }

	    return result;
	}



}
