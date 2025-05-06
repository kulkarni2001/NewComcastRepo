package com.comcast.crm.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener,ISuiteListener {

	public  ExtentReports report;
	public static ExtentTest test;
	
	
	public void onStart(ISuite suite)
	{
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
	
		//add Environment information and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("BROWSER", "Chrome-10");
	}
	
	public void onFinish(ISuite suite)
	{
		System.out.println("Report Backup");
		report.flush();
	}
	public void onTestStart(ITestResult result)
	{
		System.out.println("====>"+result.getMethod().getMethodName()+"<===Start===");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO,result.getMethod().getMethodName()+"====>Started<=====");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("====>"+result.getMethod().getMethodName()+"<===END===");
		 test.log(Status.PASS,result.getMethod().getMethodName()+"====>Completed<=====");

	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)Baseclass.sdriver;
		 String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
	
		test.addScreenCaptureFromBase64String(filepath,testName+"_"+time);


		test.log(Status.FAIL,result.getMethod().getMethodName()+"====>Failed<=====");
	}
	
	
}

