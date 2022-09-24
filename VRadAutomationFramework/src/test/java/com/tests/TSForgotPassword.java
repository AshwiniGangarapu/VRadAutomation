package com.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.ForgotPasswordPage;
import com.pom.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TSForgotPassword extends BaseClass{

	public static WebDriver driver;
	SeleniumUIUtils UI = null;
	ForgotPasswordPage forgotPwPage=new  ForgotPasswordPage();
	ExtentTest logger;
	LoginPage LP=new LoginPage();
	
	@Parameters({"browser","URL"})
	@BeforeClass
	public void init(String browser,String URL)
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get(URL);	
		driver.manage().window().maximize();
		
	}
	@BeforeMethod
	public void preSetup() throws IOException {
		System.out.println("Before test");
		//below is the name of the report.So here the name is Login Demo.
		logger = report.startTest("VRad UAT Forgot password navigation Report");
						
	}
	

	@Test()
	public void forgotPassword() throws InterruptedException{

		// clicking Forgot password in the login page
		UI.click(forgotPwPage.forgotPassword(driver));
		logger.log(LogStatus.INFO, "clicked forgot password link");

		//verifying the URL
		UI.verifyURL("http://vrad-client.eastus.azurecontainer.io/forgotpassword", driver.getCurrentUrl());

		//Enter email.
		UI.sendKeys(forgotPwPage.emailAddress(driver),"ashwini.g@sstech.us");
		logger.log(LogStatus.INFO, "entered email address as ashwini.g@sstech.us");

		//Click request a reset link	    
		UI.click(forgotPwPage.requestAResetLink(driver));
		logger.log(LogStatus.INFO, "clicked request a reset link");
		
		Assert.assertTrue(UI.isDisplayed(forgotPwPage.ClickHereToResend()));
		logger.log(LogStatus.PASS, "click to resend is displayed");		

		if ( driver.getPageSource().contains("Email Sent")){
			System.out.println( "Email sent - is present. ");
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "Email sent confirmation dispalyed.");
		} else {
			System.out.println( "Email sent - is not present. ");
			Assert.assertTrue(false);
			logger.log(LogStatus.FAIL, "Email sent confirmation not dispalyed.");
		}

		//click return to login
		UI.click(forgotPwPage.returnToLogin(driver));
		logger.log(LogStatus.INFO, "clciked return to login");
		
		Assert.assertTrue(UI.isDisplayed(LP.userName(driver)));
		logger.log(LogStatus.PASS, "Login page displayed on click return to login");
		
	}
	
public void signout(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path "+ path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
			
		System.out.println("Entered After method");
		
	
			}else if(result.getStatus() == ITestResult.SKIP) {
			            logger.log(LogStatus.SKIP, "This test skipped");
			        }
	
		report.endTest(logger);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}


}



