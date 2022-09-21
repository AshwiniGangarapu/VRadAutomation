package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage{
	
	    public By userName(WebDriver driver) {
	    	return By.xpath("//input[@id='username']");
	    }
	    
	    public By password(WebDriver driver) {
	    	return By.xpath("//input[@id='password']");
	    }
	    
	    public By alertMsgForUnableToLogin(WebDriver driver) {
	    	return By.cssSelector("div[class='MuiAlert-message css-1xsto0d'] p");
	    }
	    public By signIn(WebDriver driver) {
	    	return By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButtonBase-root  css-1n57p1h']");
	    			}
	    
	    public By viewIconForPassword(WebDriver driver) {
	    	//returnBy.xpath("By.cssSelector(\"button[class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-7hs5rr'][aria-label='toggle password visibility']"));
	    	return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-sizeMedium.css-7hs5rr>.MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.css-vubbuv");
	    }
	    
	   
}
