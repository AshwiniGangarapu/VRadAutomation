package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DispatchDetailsPage {
	
	public By getTitle(WebDriver driver) {	
		
		return By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']");

	}
	
	public By CrossSymbol(WebDriver driver) {
		return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]");
		}
}
