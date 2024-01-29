package Amazon.AmazonWebUITestProject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public ExtentReports getExtentReportObject() {
		//ExtentSparkReporter
				String path= System.getProperty("user.dir")+"//Reports//index.html";
				ExtentSparkReporter reporter =new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Result");
				reporter.config().setDocumentTitle("TestResult");
				
				//ExtentReports
				ExtentReports extent= new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester ", "Sourav");
				return extent;
		
	}

}
