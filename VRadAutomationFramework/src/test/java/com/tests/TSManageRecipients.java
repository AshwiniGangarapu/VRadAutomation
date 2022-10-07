package com.tests;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.ManageRecipientsPage;
import com.pom.NavigationPanelPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;


public class TSManageRecipients extends BaseClass {

	WebDriver driver = null;
	SeleniumUIUtils UI = null;
	CommonUtils CU = null;
	ManageRecipientsPage MR=new ManageRecipientsPage();
	NavigationPanelPage NP = new NavigationPanelPage();

	String currentURL;

	ExtentTest logger;
	XSSFSheet login = null;
	XSSFSheet recipient = null;


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
		recipient = readSheet("Recipients");
	}


	@BeforeMethod
	public void setUp() throws IOException {		
		System.out.println("Before test");
		//below is the name of the report.So here the name is Login Demo.

		CU.validSignIn(driver, login);
	}


	@Test(priority = 1,groups={"regression"})
	public void VerifySelectingRecordAndClickingEditRetrivesExactRecord() throws InterruptedException {

		logger = report.startTest("VRad UAT Verify click edit on a record retrives exact record details Report");


		// EXPAND NAVIGATION PANEL
		//driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");	

		//Click manage recipients
		//driver.findElement(By.xpath("//div[@class='MuiListItemButton-root MuiListItemButton-gutters MuiButtonBase-root css-n8fm4g']")).click();
		UI.click(MR.expandedManageRecipientsButton(driver));
		logger.log(LogStatus.INFO, "clicked expand button");

		Thread.sleep(2000);
		//getting all the rows in the recipients table.
		List<WebElement> recRows = driver.findElements(By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]"));
		logger.log(LogStatus.INFO, "Got all rows from table recipients");
		String RecordResham = null;
		System.out.println(recRows.size());
		System.out.println("before for loop");

		for(int i=0; i<recRows.size(); i++) {
			System.out.println("entered for loop");
			System.out.println(recRows.get(i).getText());

			if(recRows.get(i).getText().contains("resham")) {

				System.out.println(recRows.get(i).getText());

				RecordResham=recRows.get(i).getText();
				logger.log(LogStatus.INFO, "selected the recipeint record with name Resham" +RecordResham);
				System.out.println("recp row details : "+ RecordResham);
				recRows.get(i).findElement(By.cssSelector("[data-testid=\"EditIcon\"]")).click();
				logger.log(LogStatus.INFO, "clicked edit icon for the recipient name Resham");
				break;
				//recRows.get(i)
			}
		}

		Thread.sleep(4000);
		//getting the sub tilte and verifying it.
		String TitleEditRecipients=driver.findElement(By.cssSelector("div[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 css-12nqbl7'] h6[class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']")).getText();
		System.out.println(TitleEditRecipients);
		Assert.assertEquals("Edit Recipient",TitleEditRecipients,"Edit Recipient");
		logger.log(LogStatus.PASS, "navigated to edit recipient page");

		//retriving the text entered in the firstname,lastname and phone number
		System.out.println(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"));

		System.out.println(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"));

		System.out.println(driver.findElement(By.xpath("//input[@id='MobileNumber']")).getAttribute("value"));

		//verifying the edit page has the same text of the record Resham.            
		Assert.assertTrue(RecordResham.contains(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value")));  
		logger.log(LogStatus.PASS, "first name displayed matches the selected record");

		Assert.assertTrue(RecordResham.contains(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value")));
		logger.log(LogStatus.PASS, "last name displayed matches the selected record");

		Assert.assertTrue(RecordResham.contains(driver.findElement(By.xpath("//input[@id='MobileNumber']")).getAttribute("value")));
		logger.log(LogStatus.PASS, "phone number displayed matches the selected record");
		System.out.println("Assert pass");

		//clicking cancel button
		driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButtonBase-root  css-xuvwmr']")).click();
		logger.log(LogStatus.INFO, "clicked Cancel button");
		//clicking cross button.
		// $('.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid="CloseIcon"]')
		driver.findElement(By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]")).click();
		logger.log(LogStatus.INFO, "clicked cross button");
	}

	@Test(priority = 0,groups= {"smoke"})
	public void VerifyCreateRecipient() throws InterruptedException {

		logger = report.startTest("VRad UAT Verify create new recipient Report");

		// EXPAND NAVIGATION PANEL
		//driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");	

		//Click manage recipients
		//driver.findElement(By.xpath("//div[@class='MuiListItemButton-root MuiListItemButton-gutters MuiButtonBase-root css-n8fm4g']")).click();
		UI.click(MR.expandedManageRecipientsButton(driver));
		logger.log(LogStatus.INFO, "clicked expand button");	

		UI.waitForElementVisibility(MR.createNewRecipient(driver), driver);

		//gettting the Main title
		String TitleManageRecipients=UI.getText(MR.mainTitle(driver));
		//String TitleManageRecipients=driver.findElement(By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']")).getText();
		System.out.println(TitleManageRecipients);
		String ExpectedManageReipientsTitle="Manage Recipients";
		Assert.assertEquals(ExpectedManageReipientsTitle,TitleManageRecipients);
		logger.log(LogStatus.PASS, "verified the title");

		//create new recipient
		UI.click(MR.createNewRecipient(driver));
		//driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall MuiButton-disableElevation MuiButtonBase-root  css-1j3byya']")).click();
		logger.log(LogStatus.INFO, "clicked create new recipient");
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows);

		//getting the sub tilte
		//String TitleCreateRecipients=UI.getElement(By.cssSelector("div[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 css-12nqbl7'] h6[class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']")).getText();
		String TitleCreateRecipients = UI.getText(MR.subTitle(driver));
		System.out.println(TitleCreateRecipients);
		Assert.assertEquals("Create New Recipient",TitleCreateRecipients,"Create New Recipient");
		logger.log(LogStatus.PASS, "verified the subtitle");

		UI.sendKeys(MR.recipientFirstName(driver),data.getDataAsString(recipient,"First Name", 1));
		UI.sendKeys(MR.recipientLastName(driver),data.getDataAsString(recipient,"Last Name", 1));
		UI.sendKeys(MR.recipientMobileNumber(driver),Double.toString(data.getDataAsNumeric(recipient,"Phone Number", 1)));	
		UI.sendKeys(MR.recipientCountryCode(driver),Integer.toString((int)data.getDataAsNumeric(recipient,"Counrty Code", 1)));

		logger.log(LogStatus.INFO, "entered values from excel");		

		UI.click(MR.recipientStatus(driver));
		UI.click(MR.recipientStatusActive(driver));

		UI.click(MR.recipientNotificationPreference(driver));
		UI.click(MR.recipientNotificationText(driver));


		UI.click(MR.recipientCancelButton(driver));
		//driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButtonBase-root  css-xuvwmr']")).click();
		logger.log(LogStatus.INFO, "clicked cancel button");

		UI.click(MR.recipientCrossSymbol(driver));
		// $('.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid="CloseIcon"]')
		//driver.findElement(By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]")).click();
		logger.log(LogStatus.INFO, "clicked cross symbol");

	}


	@Test(priority = 2,enabled=false)
	public void VerifyUsersRolesAccessiblity() throws Exception {	


		String roleIdentified= CU.getRoles(data.getDataAsString(login,"UserName", 2),driver);

		if(roleIdentified.equals("A")) {

			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"EditIcon\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"DeleteOutlineIcon\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"VisibilityOutlinedIcon\"]")).isDisplayed());

		}else if(roleIdentified.equals("R")) {

			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"EditIcon\"]")).isDisplayed());	    	  	
			CU.DeleteIconAvailable();

		}else if(roleIdentified.equals("D")) {	    	

			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"VisibilityOutlinedIcon\"]")).isDisplayed());
			CU.EditIconAvailable();
			CU.DeleteIconAvailable();   
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
