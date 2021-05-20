package com.sinch.ipx.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {

	public static ExtentHtmlReporter extentHtmlReport;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	public static String extentReportPath;
	
	public static void extentReportSetUp(String browserName) {
		
		if (extentReport == null) {
			String folderName = "test-output/ExtentReport";
			File[] allFiles = null;

			File directory = new File(folderName);
			if (directory.isDirectory())
				allFiles = directory.listFiles();

			if (allFiles != null)
				for (File file : allFiles)
					file.delete();
		
		String stringDate = (new SimpleDateFormat("dd-MMM-hh-mm")).format(new Date());
		String fileName = "report-" + stringDate + ".html";
		System.out.println("extent:"+System.getProperty("user.dir") + "\\" + folderName + "\\" + fileName);
		extentReportPath = System.getProperty("user.dir") + "\\" + folderName + "\\" + fileName;
		extentHtmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\" + folderName + "\\" + fileName);
		extentHtmlReport.loadXMLConfig(System.getProperty("user.dir")+"\\ReportConfig.xml");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentHtmlReport);
		
		
		extentReport.setSystemInfo("hostname", "My host");
		extentReport.setSystemInfo("OS", "win-10");
		extentReport.setSystemInfo("browser", browserName);
		extentReport.setSystemInfo("project", "IPX");
	}
	
	}
	public static void endReport() {
		extentReport.flush();
	}
}
