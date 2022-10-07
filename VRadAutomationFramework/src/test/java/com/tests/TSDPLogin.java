package com.tests;

import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.DashboardPage;
import com.pom.LoginPage;
import com.pom.NavigationPanelPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.ExcelUtils;
import com.utils.SeleniumUIUtils;

public class TSDPLogin extends BaseClass{
	
	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	LoginPage loginPage=new LoginPage();
	CommonUtils CU = null;
	DashboardPage DP = new DashboardPage();
	NavigationPanelPage NP = new NavigationPanelPage();

	//intializing a variable of type ExtentTest class.
	//ExtentTest is used to create the body of the report.
	ExtentTest logger;
	XSSFSheet sheet = null;			

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
		//readExcelDataToArray();
		System.out.println("end of before class");
	}

	@Test(dataProvider = "readExcelDataToArray")
		public void SignInWithVariousData(String roleDescription,String userName,String password) throws InterruptedException, IOException{
		//the column names will be the parameters.
		
		logger = report.startTest("verifying data provider for login test case");

		UI.sendKeys(loginPage.userName(driver),userName);
		logger.log(LogStatus.INFO, "entered username as : "+ userName);
		
		UI.sendKeys(loginPage.password(driver),password);
		logger.log(LogStatus.INFO, "entered username as : "+ password);

		UI.click(loginPage.signIn(driver));
		logger.log(LogStatus.INFO, "clicked signin button");

		UI.isDisplayed(DP.expandShowingListBox());
		Assert.assertTrue(UI.isDisplayed(DP.expandShowingListBox()));

		//verifying the URL
		Assert.assertEquals(driver.getCurrentUrl(),DashboardURL,driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "verified that valid login navigating to dashboard URL");
		
		UI.click(NP.CollapsedUserAccountButton(driver));
		
		UI.click(NP.CollapsedLogoutbButton(driver));
		
				
	}
	
	
	@AfterClass
	public void signout(ITestResult result) {
		
		System.out.println("start of after class");

		if(result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path "+ path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));

			System.out.println("Entered After method");


		}else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "This test skipped");
		}
		
		report.endTest(logger);
		driver.quit();
	}
	
	@DataProvider
	public String[][] readExcelDataToArray() throws IOException {
		
		sheet = readSheet("Login");
		
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(colCount);
		
		String[][] data = new String[rowCount][colCount];
		
		for(int i=0;i<=rowCount-1;i++) {
			
			for(int j=0;j<=colCount-1;j++) {
				
				DataFormatter df = new DataFormatter();
				
				data[i][j]= df.formatCellValue(sheet.getRow(i+1).getCell(j));										
			}
			
			}
		for(String[] dataArr : data) {
			
			System.out.println(Arrays.toString(dataArr));
			
		}
		
		return data;
			
		}


}


