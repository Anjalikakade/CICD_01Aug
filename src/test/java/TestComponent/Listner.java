package TestComponent;

import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import Resource.ExtendsReportClass;

public class Listner extends BaseComponent implements ITestListener
{
	// access extent object
	ExtentReports extent = ExtendsReportClass.getReportObject();
	ExtentTest test;
	//threadsafe to solve the concurrency issues create object of threadlocal class
	
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ITestListener.super.isEnabled();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		 test =extent.createTest(result.getMethod().getMethodName());
		 //push test object into threadlocal so it will create unique thread id
		 thread.set(test); // set unique thrad id for each test so no concurency issues is there
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		
		//pass code
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		thread.get().fail(result.getThrowable());
		//take screenshot
		String filepath = null;
		//read driver filed  from current class,current real class.driver life is associated with each class of the test casec
		try 
		{
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		//attach resultby TestNgListner
		//test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		thread.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName()); // to handle concurrency see above code
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	

}
