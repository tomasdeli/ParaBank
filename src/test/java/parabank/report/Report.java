package parabank.report;

import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {
	/*** VARIABLES ***/
	// Variables
	protected static final String BASE_PATH = System.getProperty("user.dir");
	protected static final String TYPE_FILE = ".html";
	
	// Report
	protected static ExtentReports report;
	protected static ExtentSparkReporter spark;
	
	/*** METHODS ***/
	// Report configuration
	public static ExtentReports configurate() {
		Calendar date = Calendar.getInstance();
		
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int minute = date.get(Calendar.MINUTE);
		int second = date.get(Calendar.SECOND);
		
		String dateFormatted = String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
		
		String reportName = "REPORT " + dateFormatted;
		
		String reportsDir = BASE_PATH + "\\target\\reports\\" + reportName;
		String reportsPath =  reportsDir + "\\" + reportName + TYPE_FILE;
		
		new java.io.File(reportsDir).mkdirs();
		
		spark = new ExtentSparkReporter(reportsPath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Report Test");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		
		return report;
	}
}