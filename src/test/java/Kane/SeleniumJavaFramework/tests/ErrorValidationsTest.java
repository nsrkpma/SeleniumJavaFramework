package Kane.SeleniumJavaFramework.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Kane.SeleniumJavaFramework.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},invocationCount=1)
	public void incorrectEmailPasswordErrorValidation() throws IOException, InterruptedException {
		landingPage.login("Kane@gma23il.com", "Kane@1213");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorToast());
	}
	
	@Test(groups={"ErrorHandling"},priority=1)
	public void invalidEmailValidation() {
		landingPage.login("Kane", "Kane@1213");
		Assert.assertEquals(landingPage.verifyEmailErrorToast(), true);
	}

	@Test(groups={"ErrorHandling"},priority=1)
	public void TestMethod() {
		System.out.println("Hello World");
	}
}
