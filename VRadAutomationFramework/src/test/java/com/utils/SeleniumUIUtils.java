package com.utils;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SeleniumUIUtils {
	SeleniumWaitUtils wait = new SeleniumWaitUtils();
	WebDriver driver = null;

	public SeleniumUIUtils(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public void verifyURL(String URL,String currentURL){

		Assert.assertEquals(currentURL,URL,currentURL);
		
	}
	
	public void listBoxOption(By element,String value) {
		wait.doWaitForElement(element, driver).click();

		List<WebElement> c =element.findElements((SearchContext) By.xpath("./child::*"));
		// iterate child nodes
		System.out.println(c.size());

		for ( WebElement i : c ) {

			if(i.getText().equalsIgnoreCase(value)) {
				i.click();
			}
		}
	}

	public void mouseHover(By element) {

		Actions action = new Actions(driver); 
		 wait.doWaitForElement(element, driver);
		action.moveToElement(driver.findElement(element)).perform();
		
	}

	public void click(By element) {
		
		wait.doWaitForElement(element, driver).click();
		
	}
	
	public void clear(By element) {
		wait.doWaitForElement(element, driver).clear();
	}

	public void sendKeys(By element, String value) {
		
		
		WebElement ele = wait.doWaitForElement(element, driver);
		ele.click();
		ele.clear();
		ele.sendKeys(value);
		
	}
	
   /* public void sendKeys(By element, Double value) {
		
		
		WebElement ele = wait.doWaitForElement(element, driver);
		ele.click();
		ele.clear();
		Integer.toString();
		ele.sendKeys(value);
		
	}*/

	public WebElement getElement(By eachAction) {
		
		return wait.doWaitForElement(eachAction, driver);
		
	}
	
public List<WebElement> getElements(By element) {
	wait.doWaitForElement(element, driver);
		return driver.findElements(element);
		
	}

	public String getText(By element) {
		
		wait.doWaitForElement(element, driver);
		return getElement(element).getText();
		
	}
	
public String getAttribute(By element,String key) {
		
		wait.doWaitForElement(element, driver);
		return getElement(element).getAttribute(key);
		
	}
public boolean isEnabled(By element) {
	
		return wait.doWaitForElement(element, driver).isEnabled();
		
	}
    public boolean isDisplayed(By element) {
		
		return wait.doWaitForElement(element, driver).isDisplayed();
		
	}
    
    public void asserting(String expText,By element) {
    	
    	 wait.doWaitForElement(element, driver);
    	 Assert.assertEquals(expText,element);
	
    }
	public WebElement waitForElementVisibility(By element, WebDriver driver) {
		return wait.waitForElementVisisbility(driver, element);
	}
	
	
	
	public String takeSnapShot(WebDriver webdriver, String testCaseName) {
    	File SrcFile, DestFile = null;
    	try {
    	String basePath = System.getProperty("user.dir")+"\\ScreenShots\\";
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        DestFile=new File(basePath+testCaseName+".png");
        FileUtils.copyFile(SrcFile, DestFile);
    	}catch(Exception ee) {
    		ee.printStackTrace();
    	}
       return DestFile.getAbsolutePath();
    }
	
    
public int columnPosition(WebElement Table,String column_name) {
		
		// Purpose: gives the column position of the header name in given table.
		// Inputs : column or header name
		// Output : column number of the input header name


		// Getting the column number with column name or header as Products.
		List<WebElement> th = Table.findElements(By.tagName("th"));
		
		int col_position = 0;
	   		
		for (int i = 0; i < th.size(); i++) {
			System.out.println(th.get(i).getText());

			if (column_name.equalsIgnoreCase(th.get(i).getText())) {
				col_position = i + 1;
				System.out.println(col_position);

			}

		}
		return col_position;
	}
    
public void selectDropDownByValueUsingAttribute(By elements, String value,String keyAttribute) {
    List<WebElement> dropdown = getElements(elements);//getElements is the method in same class so we can call directly.
   // Loop to print one by one
   for(int j = 0; j < dropdown.size(); j++) {
       System.out.println(dropdown.get(j).getText());
      if(dropdown.get(j).getAttribute(keyAttribute).toLowerCase().equals(value.toLowerCase())) {
           dropdown.get(j).click();
           break;
       }
   }
}

}

