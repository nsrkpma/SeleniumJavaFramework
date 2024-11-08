package Kane.SeleniumJavaFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenrReporterNg {

	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Kane's Automation Result");
		reporter.config().setDocumentTitle("Kane Test");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nirmal Rawat");
		return extent;
	}
}
