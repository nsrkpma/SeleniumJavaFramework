package Kane.SeleniumJavaFramework.resources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNg {

    public static String runFolderPath;  // <--- ADD THIS

    public static ExtentReports getReportObject() {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        runFolderPath = System.getProperty("user.dir") 
                + File.separator + "reports"
                + File.separator + "Run_" + timeStamp;

        new File(runFolderPath).mkdirs();

        String reportPath = runFolderPath + File.separator + "AutomationReport.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setReportName("UI Automation Execution Report");
        spark.config().setDocumentTitle("Automation Test Results");
        spark.config().setTimeStampFormat("EEEE, MMM dd yyyy, hh:mm a '('zzz')'");
        spark.config().setCss(".badge { font-size: 14px; }");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);

        extent.setSystemInfo("Tester", "Nirmal Rawat");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java", System.getProperty("java.version"));

        return extent;
    }
}
