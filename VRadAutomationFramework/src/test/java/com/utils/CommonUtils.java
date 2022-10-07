package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.pom.DashboardPage;
import com.pom.LoginPage;
import com.pom.ManageUsersPage;
import com.pom.NavigationPanelPage;

public class CommonUtils extends BaseClass {

	WebDriver driver = null;

	SeleniumUIUtils UI = new SeleniumUIUtils(driver);;

	DashboardPage DP = new DashboardPage();
	LoginPage LP = new LoginPage();
	ManageUsersPage MP=new ManageUsersPage();

	String UserName;	

	static NavigationPanelPage NP = new NavigationPanelPage();
	
	public CommonUtils(WebDriver driver) {
		this.driver= driver;
	}


	public String getUserName(XSSFSheet sheet) throws IOException {
		
		String UserName=data.getDataAsString(sheet,"UserName", 2);
		return UserName;
	}


	public void validSignIn(WebDriver driver, XSSFSheet sheet) throws IOException{

		UI = new SeleniumUIUtils(driver);

		UI.sendKeys(LP.userName(driver),data.getDataAsString(sheet,"UserName", 2));
		//logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"UserName", 2));

		UserName=data.getDataAsString(sheet,"UserName", 2);

		UI.sendKeys(LP.password(driver),data.getDataAsString(sheet,"Password", 2));
		//logger.log(LogStatus.INFO, "entered username as : "+ data.getDataAsString(sheet,"Password", 2));

		UI.click(LP.signIn(driver));
		//logger.log(LogStatus.INFO, "clicked signin button");
		System.out.println("Logged in");

		UI.isDisplayed(DP.expandShowingListBox());
		Assert.assertTrue(UI.isDisplayed(DP.expandShowingListBox()));
		//logger.log(LogStatus.INFO, "verified showing is displayed");

		//verifying the URL
		
		Assert.assertEquals(driver.getCurrentUrl(),DashboardURL,driver.getCurrentUrl());
		//logger.log(LogStatus.PASS, "verified that valid login navigating to dashboard URL");

		//return UserName;

		// WebElement result = new WebDriverWait(driver, Duration.ofSeconds(50))
		//    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#demo-simple-select")));

	}

	public void Logout(WebDriver driver) {

		System.out.println("logout process");

		UI.click(NP.ExpandedUserAccountButton(driver));
		//logger.log(LogStatus.INFO, "clicked user account button");

		
		UI.waitForElementVisibility(NP.ExpandedLogoutButton(driver), driver);
		//verifying logout collapse icon is displayed.		
	//	 if(UI.getElement(NP.ExpandedLogoutButton(driver)).isEnabled()) {
        //    Boolean isEnable = UI.getElement(NP.ExpandedLogoutButton(driver)).isEnabled();
         //   Boolean display = UI.getElement(NP.ExpandedLogoutButton(driver)).isDisplayed();
          //  Boolean sele = UI.getElement(NP.ExpandedLogoutButton(driver)).isSelected();
            
         //   System.out.println(isEnable + " >> " + display + " >> " + sele);
			UI.click(NP.ExpandedLogoutButton(driver));
			//logger.log(LogStatus.INFO, "clicked logout button");


			Assert.assertTrue(UI.isDisplayed(LP.userName(driver)));
			//logger.log(LogStatus.INFO, "verified user name web element is displayed");   			

			String currentURL=driver.getCurrentUrl();
			UI.verifyURL(LoginURL,currentURL);
			//	logger.log(LogStatus.INFO, "verified verified Login URL");
			System.out.println("logout");
	//	 }

	}


	public String getRoles(String UserName,WebDriver driver) throws InterruptedException {

		String userNameTruncated;	
		List<WebElement> userRecords;
		String userRole = null;
		String clicked = null;

		userNameTruncated=StringUtils. truncate(UserName,10);
		System.out.println(userNameTruncated);

		//navigation panel expand
		//driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']")).click();

		//Click manage users
		//$('[data-testid="ManageAccountsIcon"]')
		UI.click(NP.ExpandedManageUser());      
		//driver.findElement(By.cssSelector("svg[data-testid='ManageAccountsIcon']")).click();

		//Explicit wait till the rows in table are displayed.
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"MUIDataTableBodyRow-0\"]")));
		//$('[data-testid="MUIDataTableBodyRow-0"]')

		UI.isDisplayed(MP.firstRowInUsersTable());
		//clicking the pagination rows dropdown to see the available options in drop down
		// //div[@id='pagination-rows']
		//$('#pagination-rows')
		UI.click(MP.PaginationRowsDropdown());
		//driver.findElement(By.id("pagination-rows")).click();

		//selecting the dropdown option 20
		//	$('#pagination-menu-list>li[data-value="20"]')
		// //ul[@id="pagination-menu-list"]//li[@data-value='20']
		UI.click(MP.selectRowsPerPage());
		//driver.findElement(By.cssSelector("#pagination-menu-list>li[data-value=\"20\"]")).click();	

		do {
			//list of all row (tr) from the table is retrieved to the list
			//tr[starts-with(@data-testid, "MUIDataTableBodyRow")]

			userRecords =UI.getElements(MP.AllRowsInTableDisplayed());					

			//userRecords= driver.findElements(By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]"));

			for(int i=0; i<userRecords.size(); i++) {

				System.out.println("users in page   "+userRecords.get(i).getText());
				//String allUserRecordsText=userRecords.get(i).getText();

				//verifying if this row has the user email.
				if(userRecords.get(i).getText().startsWith(userNameTruncated)){
					System.out.println("users in page   "+userRecords.get(i).getText());
					System.out.println("found the username record from the list");
					//needs to truncate email as it displays max of 14 characters.
					userRole=StringUtils. truncate(userRecords.get(i).getText(),14,1);
					System.out.println(userRole);	

				}
			}
			// writing if because if there are only 20 records the while fails if it is not enabled for first iteration itself 

			if(UI.isEnabled(MP.NextPageArrow())) {
				UI.click(MP.NextPageArrow());
				//driver.findElement(By.cssSelector("#pagination-next")).click();//last time click is disabled.so error.
				clicked="yes";
			}
			else {
				clicked="no";
			}
			Thread.sleep(2000);
		}while(UI.isEnabled(MP.NextPageArrow())||clicked=="yes");
		
		UI.click(MP.CrossSymbol());

		return userRole;  
		
		
		//driver.findElement(By.xpath("//tr[@data-testid="MUIDataTableBodyRow-i")]//td[@data-colindex='1']"

	}


	public void EditIconAvailable() throws Exception  
	{         
		try   
		{    

			if(UI.isDisplayed(MP.EditIconSymbol()))
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
			if(UI.isDisplayed(MP.DeleteIconSymbol())) {

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


	public FileInputStream readFile(String filePath) {
		File file = new File(filePath);
		FileInputStream read = null;
		try {
			read =  new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			new FileNotFoundException("File not found in >>> "+ filePath);
		}
		return read;
	}
	
	

}

