package com.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

public class TSManageUsers extends BaseClass{

	static WebDriver driver = null;
	SeleniumUIUtils UI = null;
	CommonUtils CU = null;
	ExtentTest logger;
	XSSFSheet login = null;

	String currentURL;
	String Login="http://vrad-client.eastus.azurecontainer.io/login";
	String UserName="ashwini.g@sstech.us";
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
		login = readSheet("Login");
	}

	@BeforeMethod
	public void setUp() throws IOException {		
		System.out.println("Before test");
		//below is the name of the report.So here the name is Login Demo.

		CU.validSignIn(driver, login);
	}



	@Test
	public void VerifyUsersCorrespondingToTheShowing() throws InterruptedException {

		logger = report.startTest("VRad UAT Verify users displayed correspond to the showing selected Report");

		TSManageUsers mu=new TSManageUsers();

		// EXPAND NAVIGATION PANEL
		driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();
		logger.log(LogStatus.INFO, "clicked expand button");	
		//Click manage users
		//$('[data-testid="ManageAccountsIcon"]')
		driver.findElement(By.cssSelector("svg[data-testid='ManageAccountsIcon']")).click();
		logger.log(LogStatus.INFO, "clicked manage users button in navigation panel");

		//manage users title not for reporting user
		// driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-subtitle1.css-g72m4a")).getText();
		//div.jss15>h6

		//view users title        
		driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-subtitle1.css-g72m4a")).getText();

		//Explicit wait till the rows in table are displayed.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"MUIDataTableBodyRow-0\"]")));
		//$('[data-testid="MUIDataTableBodyRow-0"]')

		mu.verifyShowingActive(logger);
		mu.verifyShowingInActive(logger);
		mu.verifyShowingAllUsers(logger);

		System.out.println("working");

		Thread.sleep(1000);

		//clicking close icon on right side corner.    
		driver.findElement(By.cssSelector("[class=\"MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b\"]>svg[data-testid=\"CloseIcon\"]")).click();       
		//  $('[class="MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b"]>svg[data-testid="CloseIcon"]') 

		Thread.sleep(1000);
	}

	@Test(enabled=false)
	public void VerifyUsersRolesAccessiblity() throws Exception {		

		TSManageUsers mu=new TSManageUsers();
		String roleIdentified= CU.getRoles(UserName,driver);

		if(roleIdentified.equals("A")) {

			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"EditIcon\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("[data-testid=\"DeleteOutlineIcon\"]")).isDisplayed());
			System.out.println("verified the accessbility of Admin user");

		}else if(roleIdentified.equals("R")) {

			CU.DeleteIconAvailable();
			CU.EditIconAvailable();

			System.out.println("verified the accessbility of Reporting user");

		}else if(roleIdentified.equals("D")) {

			CU.DeleteIconAvailable();
			CU.EditIconAvailable();

			System.out.println("verified the accessbility of Dispatching user");
		}   


	}

	public void EditIconAvailable() throws Exception  
	{         
		try   
		{    
			if(driver.findElement(By.cssSelector("[data-testid=\"EditIcon\"]")).isDisplayed())     
			{      
				System.out.println("test case failed");
				Assert.assertFalse(true);
			}    
		}      
		catch(Exception e)     
		{   
			System.out.println("test case passed");
			Assert.assertTrue(true);    
		}       
	}  

	public void DeleteIconAvailable() throws Exception  
	{         
		try   
		{    
			if(driver.findElement(By.cssSelector("[data-testid=\"DeleteOutlineIcon\"]")).isDisplayed())     
			{      
				System.out.println("test case failed");
				Assert.assertFalse(true);
			}    
		}      
		catch(Exception e)     
		{   
			System.out.println("test case passed");
			Assert.assertTrue(true);    
		}       
	}  

	public void verifyShowingInActive(ExtentTest logger) throws InterruptedException {

		//clicking showing dropdown
		driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-o7eamg']//div//div//div//div//div[@id='demo-simple-select']")).click();
		logger.log(LogStatus.INFO, "clicked showing drop down");
		Thread.sleep(1000);

		//clicking InActive from dropdown
		driver.findElement(By.xpath("//li[starts-with(@class,'MuiMenuItem-root MuiMenuItem-gutters')][@data-value='false']")).click();  
		logger.log(LogStatus.INFO, "selected inactive users");
		Thread.sleep(1000);

		//list of all row (tr) from the table is retrieved to the list
		List<WebElement> userRecords= driver.findElements(By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]"));
		logger.log(LogStatus.INFO, "retrieved all records");
		for(int i=0; i<userRecords.size(); i++) {

			System.out.println("Inactive users in page"+userRecords.get(i).getText());
			logger.log(LogStatus.INFO, "iterating through every record");

			String allUserRecordsText=userRecords.get(i).getText();
			logger.log(LogStatus.INFO, allUserRecordsText);

			Assert.assertTrue(allUserRecordsText.contains("Inactive"));
			logger.log(LogStatus.PASS, "is inactive record");
			if(allUserRecordsText.contains("Active")) {
				Assert.assertFalse(allUserRecordsText.contains("Active"));
				logger.log(LogStatus.FAIL, "is active record");
			}

		}
	}         

	public void verifyShowingActive(ExtentTest logger) throws InterruptedException {

		//clicking showing dropdown
		driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-o7eamg']//div//div//div//div//div[@id='demo-simple-select']")).click();
		logger.log(LogStatus.INFO, "clicked showing drop down");
		Thread.sleep(1000);

		//clicking Active from dropdown
		driver.findElement(By.xpath("//li[starts-with(@class,'MuiMenuItem-root MuiMenuItem-gutters')][@data-value='true']")).click();
		logger.log(LogStatus.INFO, "selected active users");
		Thread.sleep(1000);

		//list of all row (tr) from the table is retrieved to the list
		List<WebElement> userRecords= driver.findElements(By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]"));
		logger.log(LogStatus.INFO, "retrieved all records");
		for(int i=0; i<userRecords.size(); i++) {

			System.out.println("Active users in page"+userRecords.get(i).getText());
			logger.log(LogStatus.INFO, "iterating through every record");

			String allUserRecordsText=userRecords.get(i).getText();
			logger.log(LogStatus.INFO, allUserRecordsText);

			Assert.assertTrue(allUserRecordsText.contains("Active"));
			logger.log(LogStatus.PASS, "is active record");
			if(allUserRecordsText.contains("Inactive")) {
				Assert.assertFalse(allUserRecordsText.contains("Inactive"));
				logger.log(LogStatus.FAIL, "is inactive record");
			}
		}
	}    

	public void verifyShowingAllUsers(ExtentTest logger) throws InterruptedException {

		//clicking showing dropdown
		driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-2 css-o7eamg']//div//div//div//div//div[@id='demo-simple-select']")).click();		
		logger.log(LogStatus.INFO, "clicked showing drop down");
		Thread.sleep(1000);

		//clicking all user option from drop down
		driver.findElement(By.xpath("//li[starts-with(@class,'MuiMenuItem-root MuiMenuItem-gutters')][@data-value='null']")).click();	
		logger.log(LogStatus.INFO, "selected all users");
		Thread.sleep(1000);

		//list of all row (tr) from the table is retrieved to the list
		List<WebElement> userRecords= driver.findElements(By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]"));
		logger.log(LogStatus.INFO, "retrieved all records");
		for(int i=0; i<userRecords.size(); i++) {

			System.out.println("All users in page"+userRecords.get(i).getText());
			logger.log(LogStatus.INFO, "iterating through every record");
			String allUserRecordsText=userRecords.get(i).getText();
			logger.log(LogStatus.INFO, allUserRecordsText);
			boolean showingAllUser=allUserRecordsText.contains("Active")||allUserRecordsText.contains("Inactive");

			Assert.assertTrue(showingAllUser);
			logger.log(LogStatus.PASS, "has either active or inactive record");

			Assert.assertTrue(showingAllUser,"has either active or inactive users");


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
