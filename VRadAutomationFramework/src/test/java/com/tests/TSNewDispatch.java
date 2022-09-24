package com.tests;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.NavigationPanelPage;
import com.pom.NewDispatchPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;



public class TSNewDispatch extends BaseClass{

	ExtentTest logger;

	NavigationPanelPage NP = new NavigationPanelPage();
	NewDispatchPage NDP = new NewDispatchPage();

	static WebDriver driver = null;
	SeleniumUIUtils UI = null;
	CommonUtils CU = null;

	String currentURL;

	String UserName="ashwini.g@sstech.us";

	XSSFSheet login = null;


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

	@Test(description="This TC will perform a positve flow towards send Dispatch with custom message")
	public void SendDispatch() throws InterruptedException{

		logger = report.startTest("VRad UAT Verify New Dispatch Report");

		UI.click(NP.ExpandCollapseButton());
		logger.log(LogStatus.INFO, "clicked expand button");		
		//navigation panel expand
		//driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();

		//click New Dispatch
		UI.click(NDP.NewDispatchButton(driver));
		//driver.findElement(By.xpath("//div[@class='MuiListItemButton-root MuiListItemButton-gutters MuiButtonBase-root css-1qun6fe']")).click();
		logger.log(LogStatus.INFO, "clicked New Dispatch button");

		//template drop down
		//driver.findElement(By.xpath("//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiTextField-root css-feqhe6']")).click();

		//create new dispatch
		Thread.sleep(2000);
		UI.click(NDP.CreateDispatchRadioButton(driver));
		logger.log(LogStatus.INFO, "clicked create nre dispatch radio button");
		//driver.findElement(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @value='CreateNewDispatch']")).click();
		Thread.sleep(2000);

		//text area
		UI.clear(NDP.TextArea(driver));
		//driver.findElement(By.xpath("//textarea[@class='MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputMultiline css-xin0tr']")).clear();
		UI.sendKeys(NDP.TextArea(driver), "Hi welcome to selenium java");
		//driver.findElement(By.xpath("//textarea[@class='MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputMultiline css-xin0tr']")).sendKeys("Hi welcome to selenium java");
		logger.log(LogStatus.INFO, "entered text");
		//Next button
		UI.click(NDP.NextButton(driver));
		logger.log(LogStatus.INFO, "clicked Next Button");
		//driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButtonBase-root  css-w2q1i3']")).click();

		//Drop down
		/*WebElement developers_dropdown = driver.findElement(By.id("developers-menu-toggle"));
          Select objSelect = new Select(developers_dropdown);
          objSelect.selectByIndex(2);*/

		Thread.sleep(2000);
		//String text = "uat";
		// clciking a element in select recipients check box.
		//$('.MuiList-root.MuiList-padding.css-1iaijb8>li')


		By listOfGroupRecipients = NDP.ListOfAllrecipients(driver);        		

		List<WebElement> groupList = UI.getElements(listOfGroupRecipients);

		System.out.println(groupList.size());

		for(int j = 0; j < groupList.size(); j++) {
			System.out.println(groupList.get(j).getText());
			if(groupList.get(j).getText().contains("vrad QA ssTech")) {
				groupList.get(j).click();  

			}

		}
		logger.log(LogStatus.INFO, "selected vrad QA ssTech");


		/*if(driver.findElement(By.xpath("//span[contains(text(),'vrad QA ssTech')]"))!=null)
		{
			System.out.println("vrad QA ssTech found");
		}

		else{
			System.out.println("vrad QA ssTech is Absent");
		}

		WebElement element=driver.findElement(By.xpath("//span[contains(text(),'vrad QA ssTech')]"));*/


		//WebElement parent=element.findElement(By.xpath("./.."));

		//System.out.println("Parent tagname is: " + parent.getTagName());
		//parent.click();

		//Clicking add
		UI.click(NDP.AddButton(driver));
		//driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeSmall MuiButton-outlinedSizeSmall MuiButton-disableElevation MuiButtonBase-root  css-h2ic8j']")).click();
		logger.log(LogStatus.INFO, "clicked Add Button");


		//clicking dropdown
		UI.click(NDP.DropDownGroupOrIndividual(driver));
		//driver.findElement(By.xpath("//div[@id='demo-select-small']")).click(); 
		logger.log(LogStatus.INFO, "clicked dropdown ");

		//selecting individual
		driver.findElement(By.xpath("//li[@class='MuiMenuItem-root MuiMenuItem-gutters MuiButtonBase-root css-tqol0u' and @data-value='Individual']")).click();
		logger.log(LogStatus.INFO, "selected and clicked individual");

		By listOfIndvidualRecipients = NDP.ListOfAllrecipients(driver);        		

		List<WebElement> individualList = UI.getElements(listOfIndvidualRecipients);

		System.out.println(individualList.size());

		for(int j = 0; j < individualList.size(); j++) {
			System.out.println(individualList.get(j).getText());
			if(individualList.get(j).getText().contains("ashwini g")) {
				individualList.get(j).click();  
			}

		}


		//selected ashwini g
		//driver.findElement(By.xpath("//span[contains(text(),'ashwini g')]")).click();
		logger.log(LogStatus.INFO, "selected ashwini g");
		//clicking add
		driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeSmall MuiButton-outlinedSizeSmall MuiButton-disableElevation MuiButtonBase-root  css-h2ic8j']")).click();
		logger.log(LogStatus.INFO, "click add button");

		//checking all selected elements added to selected recipients
		WebElement list=driver.findElement(By.xpath("//ul[@class='MuiList-root MuiList-padding css-dz2c49']"));
		List<WebElement> count = list.findElements(By.xpath("//li"));
		System.out.println("List size is: " +count.size());
		int size=count.size();
		logger.log(LogStatus.INFO, "found number of items in selected Recipients "+size);

		for(int i=0;i<size;i++)
		{
			System.out.println( count.get(i).getText());	

			if(count.get(i).getText().contains("vrad QA ssTech")) {
				System.out.println("vrad QA ssTech is present");
				logger.log(LogStatus.PASS, "vrad QA ssTech is present");	
			}
			if(count.get(i).getText().contains("ashwini g")) {
				System.out.println("ashwini g is present");
				logger.log(LogStatus.PASS, "ashwini g is present");	
			}
		}



		//clicking Next

		driver.findElement(By.xpath("//button[normalize-space()='Next Step']")).click();
		logger.log(LogStatus.INFO, "clicked Next Step");

		driver.findElement(By.xpath("//input[contains(@value,'Text')]")).click();
		logger.log(LogStatus.INFO, "clicked radio button Text");

		driver.findElement(By.xpath("//button[normalize-space()='Next Step']")).click();
		logger.log(LogStatus.INFO, "clicked Next Step");

		//driver.findElement(By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButtonBase-root  css-w2q1i3']")).click();
		//logger.log(LogStatus.INFO, "clicked Send Dispatch");

		UI.click(NDP.DispatchCrossSymbol(driver));
		logger.log(LogStatus.INFO, "clicked cross icon");

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
