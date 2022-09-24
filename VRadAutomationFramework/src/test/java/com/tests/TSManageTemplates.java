package com.tests;

import java.io.IOException;
import java.util.List;

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
import com.pom.LoginPage;
import com.pom.ManageTemplatesPage;
import com.pom.NavigationPanelPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

public class TSManageTemplates extends BaseClass {
	ManageTemplatesPage MT = new ManageTemplatesPage();
	NavigationPanelPage NP = new NavigationPanelPage();

	WebDriver driver = null;
	SeleniumUIUtils UI = null;
	CommonUtils CU = null;

	XSSFSheet sheet = null;

	ExtentTest logger;

	String currentURL;
	String Login="http://vrad-client.eastus.azurecontainer.io/login";
	XSSFSheet login = null;
	//String UserName="ashwini.g@sstech.us";
	//String Password="Ashwini@123";




	@Parameters({"browser","URL"})
	@BeforeClass
	public void init(String browser,String URL) throws IOException
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get(URL);	
		driver.manage().window().maximize();
		CU = new CommonUtils(driver);
		sheet = readSheet("Templates");
		login = readSheet("Login");
	}

	@BeforeMethod
	public void setUp() throws IOException {	

		CU.validSignIn(driver, login);
	}

	@Test(priority=0)
	public void VerifyTitle() throws InterruptedException {

		logger = report.startTest("VRad UAT Verify Title of ManageTemplate Report");

		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");		

		UI.click(NP.CollapsedManageTemplatesButton(driver));
		logger.log(LogStatus.INFO, "clicked Manage Template button");

		UI.waitForElementVisibility(MT.createNewTemplateButton(driver), driver);

		Assert.assertTrue(UI.isDisplayed(MT.createNewTemplateButton(driver)));
		logger.log(LogStatus.INFO, "verified Manage Template page is reached by the visibility of createNewTemplateButton");

		//gettting the Main title
		String TitleManageTemplate=UI.getText(MT.mainTitle(driver));

		Assert.assertEquals("Manage Templates",TitleManageTemplate);
		logger.log(LogStatus.PASS, "verified main title");		

		UI.click(MT.templateCrossSymbol(driver));
		logger.log(LogStatus.INFO, "clicked cross icon");

	}	

	@Test(priority=1)
	public void VerifyCreateTemplate() throws InterruptedException {

		logger = report.startTest("VRad UAT Verify creation of Template Report");

		UI.click(NP.ExpandCollapseButton());	
		logger.log(LogStatus.INFO, "clicked expand button");		

		UI.click(NP.CollapsedManageTemplatesButton(driver));
		logger.log(LogStatus.INFO, "clicked Manage Template button");
		
		UI.waitForElementVisibility(MT.createNewTemplateButton(driver), driver);

		UI.click(MT.createNewTemplateButton(driver));
		logger.log(LogStatus.INFO, "clicked Create Template button");

		UI.waitForElementVisibility(MT.templateName(driver), driver);

		Assert.assertTrue(UI.isDisplayed(MT.templateName(driver)));
		logger.log(LogStatus.PASS, "verified template name is displayed ie page reached");

		String ActualsubTitle=UI.getText(MT.subTitle(driver));
		Assert.assertEquals("Create New Template",ActualsubTitle);
		logger.log(LogStatus.PASS, "verified subtitle create new template");

		//enter test data Template Name from excel
		UI.sendKeys(MT.templateName(driver),data.getDataAsString(sheet,"Template Name", 1));
		logger.log(LogStatus.INFO, "entered template name as : "+  data.getDataAsString(sheet,"Template Name", 1));

		//enter test data Template Msg from excel
		UI.sendKeys(MT.templateMsg(driver),data.getDataAsString(sheet,"Template Msg", 1));
		logger.log(LogStatus.INFO, "entered template msg as : "+  data.getDataAsString(sheet,"Template Msg", 1));

		//verifying preview message displaying the Template msg.
		String PreviewMsg=UI.getText(MT.previewMsg(driver));
		String TemplateMsg=data.getDataAsString(sheet,"Template Msg", 1);
		Assert.assertEquals(TemplateMsg,PreviewMsg);
		logger.log(LogStatus.PASS, "verified Preview msg same as the template msg");	

		//Cliking Save Button.
		UI.click(MT.saveButton(driver));
		logger.log(LogStatus.INFO, "clicked Save button");

		//clicking cross button
		UI.click(MT.templateCrossSymbol(driver));
		logger.log(LogStatus.INFO, "clicked cross icon");

	}

	@Test(priority=2)
	public void VerifyUsersRolesAccessiblity() throws Exception {

		logger = report.startTest("VRad UAT Verify User Role access with respect to Templates Report");

		String UserName=CU.getUserName(login);

		String roleIdentified= CU.getRoles(UserName,driver); 

		UI.click(NP.ExpandCollapseButton());	
		logger.log(LogStatus.INFO, "clicked expand button");		

		UI.click(NP.CollapsedManageTemplatesButton(driver));
		logger.log(LogStatus.INFO, "clicked Manage Template button");	


		/*   
    int col_position= UI.columnPosition(MT.tableTemplates(driver),"Action");

    // getting the list of table data in Action Column.
   List<WebElement> ActionColumn = TableTemplates.findElements(By.xpath("//tr/td[" + col_position + "]"));

    System.out.println(ActionColumn.size());


    for ( WebElement eachAction: ActionColumn) { 

    	 System.out.println(eachAction.getAttribute("data-testid"));
    	 System.out.println(eachAction.getAttribute("data-testid"));


    //	UI.getElement(eachAction); 
    //	UI.isDisplayed(eachRow.(MT.viewIcon(driver)));
    	//eachRow.(MT.viewIcon(driver));*/

		if(roleIdentified.equals("A")) {

			Assert.assertTrue(UI.isDisplayed(MT.viewIcon(driver)));
			Assert.assertTrue(UI.isDisplayed(MT.editIcon(driver)));
			Assert.assertTrue(UI.isDisplayed(MT.deleteIcon(driver)));  

			logger.log(LogStatus.PASS,"Admin role aaccess verified for the user");


		}else if(roleIdentified.equals("R")) {

			Assert.assertTrue(UI.isDisplayed(MT.editIcon(driver)));
			Assert.assertTrue(UI.isDisplayed(MT.deleteIcon(driver)));  

			logger.log(LogStatus.PASS,"Reporting user role aaccess verified for the user");

		}else if(roleIdentified.equals("D")) {

			CU.DeleteIconAvailable();
			CU.EditIconAvailable();
			Assert.assertTrue(UI.isDisplayed(MT.viewIcon(driver)));

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
