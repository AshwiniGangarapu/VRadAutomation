package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {

	public By forgotPassword(WebDriver driver) {
		return By.xpath("//button[@class='MuiTypography-root MuiTypography-body2 MuiLink-root MuiLink-underlineAlways MuiLink-button css-78qpcz']");
	}
	
	public By emailAddress(WebDriver driver) {
		return By.xpath("//input[@id='email']");
	}
	
	public By requestAResetLink(WebDriver driver) {
		return By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButtonBase-root  css-1fzno9i']");
	}
	
	public By returnToLogin(WebDriver driver) {
		return  By.xpath("//button[@class='MuiTypography-root MuiTypography-body2 MuiLink-root MuiLink-underlineAlways MuiLink-button css-78qpcz']");
	}
	
	public By ClickHereToResend() {
		//$('.MuiTypography-root.MuiTypography-caption.MuiLink-root.MuiLink-underlineAlways.MuiLink-button.css-16diye3')
		return By.cssSelector(".MuiTypography-root.MuiTypography-caption.MuiLink-root.MuiLink-underlineAlways.MuiLink-button.css-16diye3");
	}
	
	
}
