package com.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.pom.DashboardPage;
import com.pom.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils; 

public class TSLogin extends BaseClass{
	
	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	LoginPage loginPage=new LoginPage();
	CommonUtils CU = null;
	DashboardPage DP = new DashboardPage();
	
	//intializing a variable of type ExtentTest class.
    //ExtentTest is used to create the body of the report.
	ExtentTest logger;
	 XSSFSheet sheet = null;			
	 
	 String currentURL;	
	
	@Parameters("browser")
	@BeforeClass
	public void init(String browser) throws IOException
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get("http://vrad-client.eastus.azurecontainer.io/");	
		driver.manage().window().maximize();
		CU = new CommonUtils(driver);
		sheet = readSheet("Login");
	}
	
	
	@BeforeMethod
	public void Setup() {
		System.out.println("Before test");
		
	}
		
@Test(description="This TC will perform valid login",priority = 2)
public void SignIn() throws InterruptedException{
	
	 logger = report.startTest("VRad UAT Verify valid Login Report");
		
	UI.sendKeys(loginPage.userName(driver),data.getDataAsString(sheet,"UserName", 2));
	logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"UserName", 2));
	
	UI.takeSnapShot(driver,"SignIn");
	
	UI.sendKeys(loginPage.password(driver),data.getDataAsString(sheet,"Password", 2));
	logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"Password", 2));
	
	UI.click(loginPage.signIn(driver));
	logger.log(LogStatus.INFO, "clicked signin button");
	
	
	
	UI.isDisplayed(DP.expandShowingListBox());
	Assert.assertTrue(UI.isDisplayed(DP.expandShowingListBox()));
    
    //verifying the URL
    Assert.assertEquals(driver.getCurrentUrl(),DashboardURL,driver.getCurrentUrl());
	logger.log(LogStatus.PASS, "verified that valid login navigating to dashboard URL");
         	
}

@Test(description="This TC will perform login with invalid password and verifies the error message.",priority = 0)
public void InvalidSignIn() throws InterruptedException{
	
	 logger = report.startTest("VRad UAT Verify invalid Login Report");

	UI.sendKeys(loginPage.userName(driver),data.getDataAsString(sheet,"UserName", 5));
	logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"UserName", 5));
	UI.sendKeys(loginPage.password(driver),data.getDataAsString(sheet,"Password", 5));
	logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"Password", 5));
	UI.click(loginPage.signIn(driver));
	logger.log(LogStatus.INFO, "clicked signin button");
		
	if(UI.isDisplayed(loginPage.alertMsgForUnableToLogin(driver))) {
		
		String text=UI.getText(loginPage.alertMsgForUnableToLogin(driver));

		String errorMsg="Unable to sign in. Please check your password and try again.";

		Assert.assertEquals(text,errorMsg, text);
		logger.log(LogStatus.PASS, "verified that error msg for invalid user");

	}

}	

@Test(description="This TC will verify the functionality of view icon in password field",priority = 1)
public void VerifyPasswordVisibility() throws InterruptedException {
	
	logger = report.startTest("VRad UAT Verify password vissibility Report");
	
	UI.sendKeys(loginPage.userName(driver),data.getDataAsString(sheet,"UserName", 5));
	logger.log(LogStatus.INFO, "entered valid user name as : "+  data.getDataAsString(sheet,"UserName", 5));
	
	UI.sendKeys(loginPage.password(driver),data.getDataAsString(sheet,"Password", 5));
	logger.log(LogStatus.INFO, "entered wroung password as : "+  data.getDataAsString(sheet,"Password", 5));
	
	//click view icon
	//loginPage.viewIconForPassword(driver).click();
	UI.click(loginPage.viewIconForPassword(driver));
	logger.log(LogStatus.INFO, "clicked password view icon");

	//verifying that on a click of view icon the icon changes to visibility off icon.
	Assert.assertEquals("VisibilityOffIcon",UI.getAttribute(loginPage.viewIconForPassword(driver), "data-testid"));
	logger.log(LogStatus.PASS, "verified that the value is visibility off");
		
	//verifying on one more click of visibility off icon it is changing to visibility icon
	UI.click(loginPage.viewIconForPassword(driver));
	Assert.assertEquals("VisibilityIcon",UI.getAttribute(loginPage.viewIconForPassword(driver), "data-testid"));
	logger.log(LogStatus.PASS, "verified that the value is visibility on");
	
  }


@AfterMethod
public void signout(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path "+ path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
			
		System.out.println("Entered After method");
		
	
			}else if(result.getStatus() == ITestResult.SKIP) {
			            logger.log(LogStatus.SKIP, "This test skipped");
			        }
		CU.Logout(driver);
		report.endTest(logger);
	}
	
	

@AfterClass
public void teardown() {
	driver.quit();
}


}