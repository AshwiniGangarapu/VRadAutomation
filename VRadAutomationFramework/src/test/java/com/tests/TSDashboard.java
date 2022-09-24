package com.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
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
import com.pom.DashboardPage;
import com.pom.DispatchDetailsPage;
import com.pom.LoginPage;
import com.pom.NavigationPanelPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

public class TSDashboard extends BaseClass {


	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	LoginPage loginPage=new LoginPage();
	CommonUtils CU = null;
	DashboardPage dashboard=new DashboardPage();
	DispatchDetailsPage DD= new DispatchDetailsPage();
	NavigationPanelPage NP=new NavigationPanelPage();

	//intializing a variable of type ExtentTest class.
	//ExtentTest is used to create the body of the report.
	ExtentTest logger;

	String currentURL;

	String UserName="ashwini.g@sstech.us";
	String Password="Ashwini@123";
	XSSFSheet login = null;


	@Parameters("browser")
	@BeforeClass
	public void init(String browser) throws IOException
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get("http://vrad-client.eastus.azurecontainer.io/");	
		driver.manage().window().maximize();
		CU = new CommonUtils(driver);
		login = readSheet("Login");
	}

	@BeforeMethod
	public void preSetup() throws IOException {
		System.out.println("Before test");
		//below is the name of the report.So here the name is Login Demo.
		

		CU.validSignIn(driver, login);

	}


	@Test(priority=0)
	public void VerifySummaryPageRecordscorrespondToShowingDate() throws InterruptedException {
		
		logger = report.startTest("VerifySummaryPageRecordscorrespondToShowingDate Report");

		//verifying the URL
		//currentURL=driver.getCurrentUrl();
		//UI.verifyURL(Dashboard,currentURL);

		//Notification
		/* if(driver.findElement(By.xpath("//div[@class='MuiSnackbar-root MuiSnackbar-anchorOriginTopCenter css-dvie3u']")).isDisplayed());
        {
        	driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit MuiIconButton-sizeSmall css-rqfns6']")).click();
        }*/
		Assert.assertTrue(UI.isDisplayed(dashboard.expandShowingListBox()));
		logger.log(LogStatus.PASS,"navigated to Dashboard page");


		//click the dropdown showing and select 90 days
		UI.click(dashboard.expandShowingListBox());
		logger.log(LogStatus.INFO, "clicked showing listbox");

		UI.selectDropDownByValueUsingAttribute(dashboard.optionsShowingListBox(), "90D", "data-value");
		logger.log(LogStatus.INFO, "selecting a value");
		
		
        UI.waitForElementVisibility(dashboard.paginationLastPage(), driver);
		//clciking the pagination last page icon
		if(UI.isEnabled(dashboard.paginationLastPage()))
		{
			UI.click(dashboard.paginationLastPage());
			logger.log(LogStatus.INFO, "clicking to navigate to last page");

		}

		//identifying the summary page table
		WebElement SummaryTable;
		
		//SummaryTable=dashboard.summaryTable();
		SummaryTable=UI.getElement(dashboard.summaryTable());
		

		//getting number of rows of that page table
		WebElement TogetRows = driver.findElement(By.xpath("//table[@class='MuiTable-root css-1y4b4fj']/tbody"));
		//WebElement TogetRows = driver.findElement(By.xpath(dashboard.summaryTable()+"/tbody"));
		//WebElement TogetRows = UI.getElement( dashboard.summaryTableBody());    
		List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
		int rowSize=TotalRowsList.size();
		logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);

		//getting the no.of columns of that page table
		//WebElement ToGetColumns = driver.findElement(By.xpath(SummaryTable+"/tbody/tr"));
		WebElement ToGetColumns = UI.getElement( dashboard.summaryTableRow());
		List<WebElement> TotalColsList = ToGetColumns.findElements(By.tagName("td"));
		System.out.println("Total Number of Columns in the table are: "+TotalColsList.size());
		int columnSize=TotalColsList.size();
		logger.log(LogStatus.INFO, "Found number of columns in each row: "+columnSize);

		//we need to get SentOndate column in a list
		//List<WebElement> Column_Sent_on =  driver.findElements(By.xpath(SummaryTable+"//tr/td[1]"));
		List<WebElement> Column_Sent_on =UI.getElements(dashboard.selectingColumnSentOn());
		logger.log(LogStatus.INFO, "selected the column SentOn");

		Date dNow = new Date( );

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		ZoneId usChicago = ZoneId.of("America/Chicago");
		System.out.println(usChicago);
		logger.log(LogStatus.INFO, "found the zone which will be used to convert time into CST "+usChicago);

		TimeZone tzInChicago = TimeZone.getTimeZone(usChicago);

		sdf.setTimeZone(tzInChicago);

		System.out.println("Current CST Date: " + sdf.format(dNow));
		logger.log(LogStatus.INFO, "current CST time is "+sdf.format(dNow));

		String currentDate=sdf.format(dNow);
		int CurrentMonth=Integer.parseInt(StringUtils. truncate(currentDate,2));
		int CurrentDay=Integer.parseInt(StringUtils. truncate(currentDate,3,2));
		System.out.println(CurrentMonth);
		logger.log(LogStatus.INFO, "current month "+CurrentMonth);
		System.out.println(CurrentDay);
		logger.log(LogStatus.INFO, "current day "+CurrentDay);

		Calendar lowerLimit=Calendar.getInstance();
		lowerLimit.add(Calendar.DATE,-90);
		System.out.println(lowerLimit.getTime());
		
		System.out.println(sdf.format(lowerLimit.getTime()));
		String lowerLimitDate=sdf.format(lowerLimit.getTime());
		logger.log(LogStatus.INFO, "Currenet Date minus number of days selected in showing "+lowerLimitDate);
		int lowerLimitMonth=Integer.parseInt(StringUtils. truncate(lowerLimitDate,2));
		int lowerLimitDay=Integer.parseInt(StringUtils. truncate(lowerLimitDate,3,2));
		System.out.println(lowerLimitMonth);
		System.out.println(lowerLimitDay);


		for(int i=0;i<rowSize-1;i++) {

			String sentOn=Column_Sent_on.get(i).getText();
			System.out.println(Column_Sent_on.get(i).getText());
			logger.log(LogStatus.INFO, "selected SentOn Date "+sentOn);

			int maxWidth = 7;

			String SentOnDate=StringUtils. truncate(sentOn, maxWidth);
			System.out.println(SentOnDate);

			int SentOnMonth= Integer.parseInt(StringUtils. truncate(SentOnDate,2));
			System.out.println(SentOnMonth);

			int SentOnDay= Integer.parseInt(StringUtils. truncate(SentOnDate,5,2));
			System.out.println(SentOnDay);

			Boolean b =true;

			if (SentOnMonth<=CurrentMonth && SentOnMonth>=lowerLimitMonth) {
				System.out.println("month is in range");
				Assert.assertEquals(true, b);
				logger.log(LogStatus.PASS, "verified month is in range");

			}
			else if(((SentOnMonth==CurrentMonth)&&(SentOnDay<=CurrentDay))&&((SentOnMonth==lowerLimitMonth)&&(SentOnDay>=lowerLimitDay))){
				System.out.println("month is same but date is in range");
				Assert.assertEquals(true, b);
				logger.log(LogStatus.PASS, "verified month is same but date is in range");
			}
			else{
				System.out.println("this particular date is more than current date or less than lower limit");
				Assert.assertEquals(false, b);
				logger.log(LogStatus.FAIL, "verified this particular date is more than current date or less than lower limit");
			}


		}//for
		
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");


	}//method

	@Test(priority=1)
	public void ActionsViewIconClick() throws InterruptedException {
		
		logger = report.startTest("Verify clicking view icon navigates to Status page Report");

		//identifying the summary page table
	/*	By SummaryTable;
		SummaryTable=dashboard.summaryTable();
	
		//getting number of rows of that page table
		WebElement TogetRows = driver.findElement(By.xpath(SummaryTable+"/tbody"));
		List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
		int rowSize=TotalRowsList.size();

		//getting the no.of columns of that page table
		WebElement ToGetColumns = driver.findElement(By.xpath(SummaryTable+"/tbody/tr"));
		List<WebElement> TotalColsList = ToGetColumns.findElements(By.tagName("td"));
		System.out.println("Total Number of Columns in the table are: "+TotalColsList.size());
		int columnSize=TotalColsList.size();*/
		
		Assert.assertTrue(UI.isDisplayed(dashboard.expandShowingListBox()));
		logger.log(LogStatus.PASS,"navigated to Dashboard page");


		//click the dropdown showing and select 90 days
		UI.click(dashboard.expandShowingListBox());
		logger.log(LogStatus.INFO, "clicked showing listbox");

		UI.selectDropDownByValueUsingAttribute(dashboard.optionsShowingListBox(), "90D", "data-value");
		logger.log(LogStatus.INFO, "selecting a value");
		
		
		//identifying the summary page table
				WebElement SummaryTable;
				
				//SummaryTable=dashboard.summaryTable();
				SummaryTable=UI.getElement(dashboard.summaryTable());
				

				//getting number of rows of that page table
				WebElement TogetRows = driver.findElement(By.xpath("//table[@class='MuiTable-root css-1y4b4fj']/tbody"));
				//WebElement TogetRows = driver.findElement(By.xpath(dashboard.summaryTable()+"/tbody"));
				//WebElement TogetRows = UI.getElement( dashboard.summaryTableBody());    
				List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
				System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
				int rowSize=TotalRowsList.size();
				logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);

				//getting the no.of columns of that page table
				//WebElement ToGetColumns = driver.findElement(By.xpath(SummaryTable+"/tbody/tr"));
				WebElement ToGetColumns = UI.getElement( dashboard.summaryTableRow());
				List<WebElement> TotalColsList = ToGetColumns.findElements(By.tagName("td"));
				System.out.println("Total Number of Columns in the table are: "+TotalColsList.size());
				int columnSize=TotalColsList.size();
				logger.log(LogStatus.INFO, "Found number of columns in each row: "+columnSize);

		//we need to SentOn column.
				List<WebElement> Column_Sent_on =UI.getElements(dashboard.selectingColumnSentOn());

		//we need to get DispatchType/Preview
		List<WebElement> DispatchMessagePreview = UI.getElements(dashboard.selectingDispatchMessagePreview());

		//we need to get ViewIcon
		//table[@class='MuiTable-root css-1y4b4fj']//tr/td[7]
		List<WebElement> actions =  UI.getElements(dashboard.actionsColumn());
		System.out.println(actions.size());
		int rowIndex=2;	
		Thread.sleep(2000);
		
		WebElement eye= actions.get(rowIndex);
		//WebElement button = eye.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeSmall css-carvac']"));
		WebElement button = eye.findElement(dashboard.viewIconButton());
	     button.click();
					
		logger.log(LogStatus.INFO, "clicked view icon");
		
		UI.waitForElementVisibility(DD.getTitle(driver), driver);
			
		UI.getText(DD.getTitle(driver));
		Assert.assertEquals("Dispatch Details",UI.getText(DD.getTitle(driver)));
				
		logger.log(LogStatus.PASS, "verified clicking view icon is navigating user to Dispatch details page." +UI.getText(DD.getTitle(driver)));

		UI.click(DD.CrossSymbol(driver));
		logger.log(LogStatus.INFO, "clicked cross button");
		
		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");


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



}//class
