package com.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ManageRecipientsPage {
	
	public By CollapsedManageRecipientsButton(WebDriver driver) {
		return By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(6) > li:nth-child(1) > div:nth-child(1)");
		
	}
	
	public By expandedManageRecipientsButton(WebDriver driver) {
		return By.xpath("//div[@class='MuiListItemButton-root MuiListItemButton-gutters MuiButtonBase-root css-n8fm4g']");
	}
	
	//@SuppressWarnings("unchecked")
	public By dataRowsInTheTable() {
		return  By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]");
	}
	public By editIcon(WebDriver driver) {
		return By.cssSelector("[data-testid=\"EditIcon\"]");
	}
		
	public By subTitle(WebDriver driver) {	
		return By.cssSelector("div[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 css-12nqbl7'] h6[class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']");
	}
	
	public By recipientFirstName(WebDriver driver) {
		return By.xpath("//input[@id='FirstName']");
	}
	
	public By recipientLastName(WebDriver driver) {
		return By.xpath("//input[@id='LastName']");
	}	

	public By recipientMobileNumber(WebDriver driver) {
		return By.xpath("//input[@id='MobileNumber']");
	}
	
	public By recipientCancelButton(WebDriver driver) {
	return By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButtonBase-root  css-xuvwmr']");
	}
	
	public By recipientCrossSymbol(WebDriver driver) {
	return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]");
	}
	
	public By mainTitle(WebDriver driver) {	
	return By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']");
	}
	
	public By createNewRecipient(WebDriver driver) {
	return By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall MuiButton-disableElevation MuiButtonBase-root  css-1j3byya']");
	}
	
	public By recipientCountryCode(WebDriver driver) {
		return By.xpath("//input[@id='CountryCode']");
	}    
	
	public By recipientStatus(WebDriver driver) {
		return 	By.xpath("//div[@id='RecipientStatus']");
	}  
	
	public By recipientStatusActive(WebDriver driver) {
		return By.xpath("//li[normalize-space()='Active']");
	}  
	
	public By recipientNotificationPreference(WebDriver driver) {
		return By.xpath("//div[@id='NotificationPreference']");
		}  
	
	public By recipientNotificationText(WebDriver driver) {
		return By.xpath("//li[normalize-space()='Text']");
	}
	
		
	}
