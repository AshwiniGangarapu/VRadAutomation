package com.tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

public class TSJavascriptExecutor  extends BaseClass{

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
		 String str = "Hi how are you welcome to Tutorialspoint";
	        String words[] = str.split(" ");
	        for(String token : words) {
	        System.out.println(token.charAt(2));}


		CU.validSignIn(driver, login);
	}



	@Test(groups= {"smoke"})
	public void VerifyScrolling() throws InterruptedException {

		 
		logger = report.startTest("VRad UAT Verify users displayed correspond to the showing selected Report");

		//TSManageUsers mu=new TSManageUsers();
		
		 String str = "Hi how are you welcome to Tutorialspoint";
	        String words[] = str.split(" ");
	        for(String token : words) {
	        System.out.println(token.charAt(2));}

		// EXPAND NAVIGATION PANEL
		driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();
		logger.log(LogStatus.INFO, "clicked expand button");	
		//Click manage users
		//$('[data-testid="ManageAccountsIcon"]')
		driver.findElement(By.cssSelector("svg[data-testid='ManageAccountsIcon']")).click();
		logger.log(LogStatus.INFO, "clicked manage users button in navigation panel");
		
		Thread.sleep(2000);

		//view users title        
		driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-subtitle1.css-g72m4a")).getText();

		Thread.sleep(2000);
		//Explicit wait till the rows in table are displayed.
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"MUIDataTableBodyRow-0\"]")));
		//$('[data-testid="MUIDataTableBodyRow-0"]')
		driver.findElement(By.id("pagination-rows")).click();
		driver.findElement(By.cssSelector("#pagination-menu-list>li[data-value=\"20\"]")).click();
		logger.log(LogStatus.INFO, "clicked 20 records per page");
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");
		 
		   //Find element by link text and store in variable "Element"        		
	      //  WebElement Element = driver.findElement(By.linkText("Linux"));

	        //This will scroll the page till the element is found		
	      //  js.executeScript("arguments[0].scrollIntoView();", Element);
	        
	        //This will scroll the web page till end.		
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	        
	        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	        
	      
	       
}
}