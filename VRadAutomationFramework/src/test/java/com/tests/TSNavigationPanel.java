package com.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.pom.NavigationPanelPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TSNavigationPanel extends BaseClass{

	WebDriver driver = null;
	SeleniumUIUtils UI = null;
	CommonUtils CU = null;
	NavigationPanelPage NP=new NavigationPanelPage();

	XSSFSheet login = null;

	String currentURL;



	@Parameters({"browser","URL"})
	@BeforeClass
	public void init(String browser,String URL) throws IOException
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get(URL);	
		driver.manage().window().maximize();
		CU = new CommonUtils(driver);
		login = readSheet("Login");
	}

	@BeforeMethod
	public void setUp() throws IOException {		

		CU.validSignIn(driver, login);
	}


	@Test(priority = 0)
	public void VerifyExpandCollapse() throws InterruptedException, IOException{

		logger = report.startTest("VRad UAT VerifyExpandCollapse Report");
		//	UserName=CU.validSignIn(driver);
		//navigation panel expand
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");

		//navigation panel collapse
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked collapse button");

		//navigation panel expand.//just to logout properly.
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");


	}

	@Test(priority = 1)
	public void verifyAllIconsOnCollapse() throws InterruptedException, IOException{

		logger = report.startTest("VRad UAT verifyAllIconsOnCollapse Report");
		//UserName=CU.validSignIn(driver);
		UI.mouseHover(NP.CollapsedManageRecipientsButton(driver));
		logger.log(LogStatus.INFO, "mouse hover on Manage recipients button");

		if(UI.isDisplayed(NP.MouseHoverTextForCollapsedButton(driver))) {

			String HouverTextRecipients=UI.getText(NP.MouseHoverTextForCollapsedButton(driver));

			Assert.assertEquals(HouverTextRecipients,"Manage Recipient", HouverTextRecipients);
			logger.log(LogStatus.PASS, "verified mouse hover text for Recipients");	

		}

		UI.waitForElementVisibility(NP.CollapsedManageUserButton(driver),driver);

		//verifying mouse hover text Manage Users
		UI.mouseHover(NP.CollapsedManageUserButton(driver));
		logger.log(LogStatus.INFO, "mouse hover on Manage users button");

		UI.waitForElementVisibility(NP.MouseHoverTextForCollapsedButton(driver),driver);


		Thread.sleep(1000);
		if(UI.isDisplayed(NP.MouseHoverTextForCollapsedButton(driver))) {

			String HouverTextUser=UI.getText(NP.MouseHoverTextForCollapsedButton(driver));

			Assert.assertEquals(HouverTextUser,"Manage Users", HouverTextUser);
			logger.log(LogStatus.PASS, "verified mouse hover text for Users");

		}

		UI.waitForElementVisibility(NP.CollapsedManageTemplatesButton(driver),driver);
		//wait for text
		//verifying mouse hover text Manage Templates
		UI.mouseHover(NP.CollapsedManageTemplatesButton(driver));
		logger.log(LogStatus.INFO, "mouse hover on Manage templates button");

		//UI.waitForElementVisibility(NP.MouseHoverTextForCollapsedButton(driver),driver);
		//Thread.sleep(2000);
		boolean b=UI.waitForTextPresentInElementLocated(NP.MouseHoverTextForCollapsedButton(driver),driver,"Manage Template");

		if(UI.isDisplayed(NP.MouseHoverTextForCollapsedButton(driver))) {

			String HouverTextTemplates=UI.getText(NP.MouseHoverTextForCollapsedButton(driver));

			Assert.assertEquals(HouverTextTemplates,"Manage Template", HouverTextTemplates);
			logger.log(LogStatus.PASS, "verified mouse hover text for Templates");

		}


		UI.waitForElementVisibility(NP.ExpandCollapseButton(),driver);
		//navigation panel expand.//just to logout properly.
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");


	}

	@Test(priority = 2)
	public void verifyUserAccountIconOnExpand() throws InterruptedException, IOException{

		logger = report.startTest("VRad UAT verifyUserAccountIconOnExpand Report");
		String UserName= CU.getUserName(login);
		//navigation panel expand

		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");

		//verifying user account button text has the login username
		//The account name is displayed partially due realestate constraint
		String account=UI.getText(NP.ExpandedUserAccountButton(driver));
		logger.log(LogStatus.INFO, "User account is:" +UI.getText(NP.ExpandedUserAccountButton(driver)));

		String partialUserName=UserName.substring(0,7);

		Assert.assertEquals(true,account.contains(partialUserName));
		logger.log(LogStatus.INFO, account+ " is the partial display of "+UserName);

		//verifying the mouse hover text on user account displaying the login UserName.     
		//mouse hover on account button to see the complete UserName.	   
		UI.mouseHover(NP.ExpandedUserAccountButton(driver));
		logger.log(LogStatus.INFO, "mouse houvered on user account");

		//verifying the user account text is dispalyed and if yes then verify its the UserName logged in.
		if(UI.isDisplayed( NP.UserAccountHoverText(driver))) {

			String UserAccountHouverTextEmail=UI.getText(NP.UserAccountHoverTextEmail(driver));

			Assert.assertEquals(UserAccountHouverTextEmail,UserName, UserAccountHouverTextEmail);
			logger.log(LogStatus.PASS, "verified the user account hover text has username.");			

		}

	}

	@Test(priority = 3)
	public void verifyIconsOnExpandCorrespondingToRoles() throws InterruptedException, IOException{

		logger = report.startTest("VRad UAT verifyIconsOnExpandCorrespondingToRoles Report");
		//navigation panel expand

		String UserName = CU.getUserName(login);
		logger.log(LogStatus.INFO,UserName+" is the user name");

		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");

		String roleIdentified= CU.getRoles(UserName,driver);
		logger.log(LogStatus.INFO,roleIdentified+" is the user role");

		if(roleIdentified.equals("A")) {

			Assert.assertEquals(UI.getText(NP.CollapsedManageRecipientsButton(driver)),"Manage Recipient");
			Assert.assertEquals(UI.getText(NP.CollapsedManageUserButton(driver)),"Manage Users");
			Assert.assertEquals(UI.getText(NP.CollapsedManageTemplatesButton(driver)),"Manage Template");

			logger.log(LogStatus.PASS,"Admin role aaccess verified for the user");

		}else if(roleIdentified.equals("R")) {

			String mrt=UI.getText(NP.CollapsedManageRecipientsButton(driver));
			Assert.assertEquals(UI.getText(NP.CollapsedManageRecipientsButton(driver)),"Manage Recipient");
			String ut=UI.getText(NP.CollapsedManageUserButton(driver));
			Assert.assertEquals(UI.getText(NP.CollapsedManageUserButton(driver)),"View Users");
			String tt=UI.getText(NP.CollapsedManageTemplatesButton(driver));
			Assert.assertEquals(UI.getText(NP.CollapsedManageTemplatesButton(driver)),"Manage Template");

			logger.log(LogStatus.PASS,"Reporting user role aaccess verified for the user");

		}else if(roleIdentified.equals("D")) {
			Assert.assertEquals(UI.getText(NP.CollapsedManageRecipientsButton(driver)),"View Recipient");
			Assert.assertEquals(UI.getText(NP.CollapsedManageUserButton(driver)),"View Users");
			Assert.assertEquals(UI.getText(NP.CollapsedManageTemplatesButton(driver)),"View Template");

			logger.log(LogStatus.PASS,"Dispatch user role aaccess verified for the user");

		}


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
