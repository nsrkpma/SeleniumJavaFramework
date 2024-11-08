package Kane.SeleniumJavaFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Kane.SeleniumJavaFramework.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage ;
	public WebDriver initializedriver() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Kane\\SeleniumJavaFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	   public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
	        String content = FileUtils.readFileToString(new File(filePath),
	                StandardCharsets.UTF_8);
	        ObjectMapper mapper = new ObjectMapper();
	        List<HashMap<String, String>> data = mapper.readValue(content, new TypeReference<List<HashMap<String, String>>>() {});
	        return data;
	    }
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException{
		driver=initializedriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl("https://rahulshettyacademy.com/client");
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png");
		FileUtils.copyFile(src,file);
		return file.getPath();
		
	}

}
