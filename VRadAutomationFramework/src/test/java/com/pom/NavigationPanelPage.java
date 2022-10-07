package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class NavigationPanelPage {
	

	public By ExpandCollapseButton() {
		return By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']");
	}
// no need of driver parameter to these methods.
	public By CollapsedManageRecipientsButton(WebDriver driver) {
		return By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(6) > li:nth-child(1) > div:nth-child(1)");
		
	}

	public By CollapsedManageUserButton(WebDriver driver) {
		return By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(6) > li:nth-child(2) > div:nth-child(1)");
	}

	public By CollapsedManageTemplatesButton(WebDriver driver) {
		return By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(6) > li:nth-child(3) > div:nth-child(1)");
	}

	public By MouseHoverTextForCollapsedButton(WebDriver driver) {
		return By.xpath("//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipArrow MuiTooltip-tooltipPlacementRight css-nnuivh']");
	}
	
	public By CollapsedUserAccountButton(WebDriver driver) {
	    return By.xpath("//div[@class='MuiListItemIcon-root css-hgb6ey']");
	}  
	
	public By CollapsedLogoutbButton(WebDriver driver) {
	    return By.xpath("//div[@class='MuiListItemIcon-root css-1aaac8c']");
	}
	
	public By ExpandedUserAccountButton(WebDriver driver) {
	  //  return By.xpath("//div[@class='MuiListItemText-root MuiListItemText-multiline css-1k0uu2f']");
	    return By.cssSelector(".MuiListItemText-root.MuiListItemText-multiline.css-1k0uu2f");
	}
	
	public By UserAccountHoverText(WebDriver driver) {
	    return By.xpath("//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipPlacementTop css-1ssj0oy']");
	}
	
	public By UserAccountHoverTextEmail(WebDriver driver) {
	    return By.xpath("//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipPlacementTop css-1ssj0oy']/p[2]");
	}
	
	public By ExpandedLogoutButton(WebDriver driver) {
	    return By.xpath("//div[@class='MuiListItemText-root css-1tsvksn']");
	}
	
	public By ExpandedManageUser() {
	return By.cssSelector("svg[data-testid='ManageAccountsIcon']");
    }
	
	    	
}
	
