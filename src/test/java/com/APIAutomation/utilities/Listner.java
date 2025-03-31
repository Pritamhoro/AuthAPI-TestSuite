package com.APIAutomation.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listner implements ITestListener 
{
	 @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Test suite " + context.getName() + " has started.");
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test " + result.getMethod().getMethodName() + " has started.");
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test " + result.getMethod().getMethodName() + " has passed.");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test " + result.getMethod().getMethodName() + " has failed.");
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test " + result.getMethod().getMethodName() + " was skipped.");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Test suite " + context.getName() + " has finished.");
	    }
}
