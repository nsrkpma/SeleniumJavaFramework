package Kane.SeleniumJavaFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("UI Automation Result");
		reporter.config().setDocumentTitle("Automation Report");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nirmal Rawat");
		extent.setSystemInfo("Browser", "Chrome");
		return extent;
	}
}
